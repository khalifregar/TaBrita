package tech.tabrita.backend.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class ResendOtpRequest(
    val email: String
)