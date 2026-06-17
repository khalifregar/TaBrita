package tech.tabrita.backend

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level
import tech.tabrita.backend.repository.InMemoryOtpRepository
import tech.tabrita.backend.repository.InMemoryUserRepository
import tech.tabrita.backend.routes.authRoutes
import tech.tabrita.backend.security.JwtService
import tech.tabrita.backend.service.AuthService
import tech.tabrita.backend.service.OtpService

fun main() {
    embeddedServer(Netty, port = 9090, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // Repositories (in-memory for demo - swap with real DB impl later)
    val userRepository = InMemoryUserRepository()
    val otpRepository = InMemoryOtpRepository()

    // Services
    val otpService = OtpService(otpRepository)
    val authService = AuthService(userRepository, otpService)

    configureSerialization()
    configureSecurity()
    configureMonitoring()
    configureStatusPages()

    routing {
        get("/") {
            call.respondText("TaBrita Auth API is running. Use /auth/register, /auth/login, /auth/verify-otp")
        }

        post("/") {
            call.respondText("POST root hit successfully")
        }

        post("/debug-register") {
            call.respondText("DEBUG REGISTER IN APPLICATION - direct")
        }

        authRoutes(authService)
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}

fun Application.configureSecurity() {
    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JwtService.verifier)
            validate { credential ->
                val email = credential.payload.getClaim("email").asString()
                if (email != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }
}

// Custom JWT verifier using our JwtService
fun Application.configureJwtVerifier() {
    // We override in the jwt block using our service
    // For simplicity the validate above uses our logic, but to integrate verifier:
    // Better to install properly.
}

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
    }
}

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<IllegalArgumentException> { call, cause ->
            call.respond(io.ktor.http.HttpStatusCode.BadRequest, mapOf("error" to (cause.message ?: "Bad request")))
        }
        exception<IllegalStateException> { call, cause ->
            call.respond(io.ktor.http.HttpStatusCode.Forbidden, mapOf("error" to (cause.message ?: "Forbidden")))
        }
    }
}