package tech.tabrita.backend.repository

import tech.tabrita.backend.model.User

interface UserRepository {
    suspend fun findByEmail(email: String): User?
    suspend fun save(user: User): User
    suspend fun updateVerification(email: String, isVerified: Boolean): Boolean
}