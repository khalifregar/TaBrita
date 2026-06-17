package tech.tabrita.backend.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequest(
    val email: String,
    val otp: String
)