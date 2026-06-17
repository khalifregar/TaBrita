package tech.tabrita.backend.repository

import tech.tabrita.backend.model.User
import java.util.concurrent.ConcurrentHashMap

class InMemoryUserRepository : UserRepository {
    private val users = ConcurrentHashMap<String, User>()

    override suspend fun findByEmail(email: String): User? {
        return users[email.lowercase()]
    }

    override suspend fun save(user: User): User {
        users[user.email.lowercase()] = user
        return user
    }

    override suspend fun updateVerification(email: String, isVerified: Boolean): Boolean {
        val lower = email.lowercase()
        val user = users[lower] ?: return false
        users[lower] = user.copy(isVerified = isVerified)
        return true
    }
}