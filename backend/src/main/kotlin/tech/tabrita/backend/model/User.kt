package tech.tabrita.backend.model

import java.time.Instant
import java.util.*

data class User(
    val id: String = UUID.randomUUID().toString(),
    val email: String,
    val passwordHash: String,
    val name: String,
    val isVerified: Boolean = false,
    val createdAt: Instant = Instant.now()
)

data class OtpRecord(
    val email: String,
    val otp: String,
    val expiresAt: Instant
)