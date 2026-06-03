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
            val request = call.receive<VerifyOtpRequest>()
            try {
                val success = authService.verifyOtp(request.email, request.otp)
                if (success) {
                    call.respond(HttpStatusCode.OK, MessageResponse("OTP verified successfully. You can now login."))
                } else {
                    call.respond(HttpStatusCode.BadRequest, MessageResponse("Invalid or expired OTP"))
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, MessageResponse(e.message ?: "Verification failed"))
            }
        }

        post("/resend-otp") {
            val request = call.receive<ResendOtpRequest>()
            try {
                authService.resendOtp(request.email)
                call.respond(HttpStatusCode.OK, MessageResponse("New OTP sent to ${request.email}"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, MessageResponse(e.message ?: "Failed to resend OTP"))
            }
        }

        post("/login") {
            val request = call.receive<LoginRequest>()
            try {
                val authResponse = authService.login(request)
                call.respond(HttpStatusCode.OK, authResponse)
            } catch (e: IllegalStateException) {
                call.respond(HttpStatusCode.Forbidden, MessageResponse(e.message ?: "Login not allowed"))
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.Unauthorized, MessageResponse(e.message ?: "Invalid credentials"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, MessageResponse("Login failed"))
            }
        }

        // Protected example route
        authenticate("auth-jwt") {
            get("/me") {
                val principal = call.principal<io.ktor.server.auth.jwt.JWTPrincipal>()
                val email = principal?.getClaim("email", String::class)
                val userId = principal?.subject
                call.respond(
                    HttpStatusCode.OK,
                    MessageResponse("Hello $email (id=$userId). You are authenticated!")
                )
            }
        }
    }
}