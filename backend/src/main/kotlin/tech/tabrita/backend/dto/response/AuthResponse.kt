package tech.tabrita.backend.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
    val user: UserResponse
)

@Serializable
data class UserResponse(
    val id: String,
    val email: String,
    val name: String,
    val isVerified: Boolean
)