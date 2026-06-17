package tech.tabrita.backend.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import tech.tabrita.backend.dto.request.LoginRequest
import tech.tabrita.backend.dto.request.RegisterRequest
import tech.tabrita.backend.dto.request.ResendOtpRequest
import tech.tabrita.backend.dto.request.VerifyOtpRequest
import tech.tabrita.backend.dto.response.MessageResponse
import tech.tabrita.backend.service.AuthService

fun Route.authRoutes(authService: AuthService) {
    post("/debug-register") {
        call.respondText("DEBUG REGISTER ROUTE HIT - body would be here")
    }

    route("/auth") {
        post("/register") {
            call.application.log.info("=== REGISTER HANDLER HIT ===")
            try {
                val rawBody = call.receiveText()
                call.application.log.info("Raw body received: $rawBody")
                val request = Json.decodeFromString<RegisterRequest>(rawBody)
                call.application.log.info("Parsed register for ${request.email}")
                authService.register(request)
                call.respond(HttpStatusCode.Created, """{"message":"Registration successful. OTP sent to ${request.email}. Please verify to activate account."}""")
            } catch (e: IllegalArgumentException) {
                call.application.log.warn("Register conflict: ${e.message}")
                call.respond(HttpStatusCode.Conflict, """{"message":"${e.message ?: "Registration failed"}"}""")
            } catch (e: Exception) {
                call.application.log.error("Register error", e)
                call.respond(HttpStatusCode.BadRequest, """{"message":"Error: ${e.message ?: e::class.simpleName}"}""")
            }
        }

        post("/verify-otp") {
            try {
                val raw = call.receiveText()
                val request = Json.decodeFromString<VerifyOtpRequest>(raw)
                val success = authService.verifyOtp(request.email, request.otp)
                if (success) {
                    call.respond(HttpStatusCode.OK, """{"message":"OTP verified successfully. You can now login."}""")
                } else {
                    call.respond(HttpStatusCode.BadRequest, """{"message":"Invalid or expired OTP"}""")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, """{"message":"Verification failed: ${e.message ?: ""}"}""")
            }
        }

        post("/resend-otp") {
            try {
                val raw = call.receiveText()
                val request = Json.decodeFromString<ResendOtpRequest>(raw)
                authService.resendOtp(request.email)
                call.respond(HttpStatusCode.OK, """{"message":"New OTP sent to ${request.email}"}""")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, """{"message":"Failed to resend OTP: ${e.message ?: ""}"}""")
            }
        }

        post("/login") {
            try {
                val raw = call.receiveText()
                val request = Json.decodeFromString<LoginRequest>(raw)
                val authResponse = authService.login(request)
                // For simplicity, respond as text json (AuthResponse has token)
                call.respondText("{\"token\":\"${authResponse.token}\",\"user\":{\"id\":\"${authResponse.user.id}\",\"email\":\"${authResponse.user.email}\",\"name\":\"${authResponse.user.name}\",\"isVerified\":${authResponse.user.isVerified}}}", status = HttpStatusCode.OK)
            } catch (e: IllegalStateException) {
                call.respond(HttpStatusCode.Forbidden, """{"message":"${e.message ?: "Login not allowed"}"}""")
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.Unauthorized, """{"message":"${e.message ?: "Invalid credentials"}"}""")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, """{"message":"Login failed: ${e.message ?: ""}"}""")
            }
        }

        // Protected example route
        authenticate("auth-jwt") {
            get("/me") {
                val principal = call.principal<io.ktor.server.auth.jwt.JWTPrincipal>()
                val email = principal?.getClaim("email", String::class)
                val userId = principal?.subject
                call.respond(HttpStatusCode.OK, """{"message":"Hello $email (id=$userId). You are authenticated!"}""")
            }
        }
    }
}