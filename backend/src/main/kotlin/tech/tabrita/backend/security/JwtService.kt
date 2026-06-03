package tech.tabrita.backend.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import java.util.*

object JwtService {
    private const val SECRET = "super-secret-key-for-ta-brita-demo-change-in-prod"
    private const val ISSUER = "ta-brita-auth"
    private const val AUDIENCE = "ta-brita-users"
    private const val VALIDITY_IN_MS = 36_000_00 * 24 // 24 hours

    private val algorithm = Algorithm.HMAC256(SECRET)

    val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun generateToken(userId: String, email: String): String {
        val now = Date()
        return JWT.create()
            .withIssuer(ISSUER)
            .withAudience(AUDIENCE)
            .withSubject(userId)
            .withClaim("email", email)
            .withIssuedAt(now)
            .withExpiresAt(Date(now.time + VALIDITY_IN_MS))
            .sign(algorithm)
    }

    fun verifyToken(token: String) = try {
        verifier.verify(token)
    } catch (e: Exception) {
        null
    }
}