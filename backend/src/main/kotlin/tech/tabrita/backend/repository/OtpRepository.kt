package tech.tabrita.backend.repository

import tech.tabrita.backend.model.OtpRecord

interface OtpRepository {
    suspend fun save(otpRecord: OtpRecord)
    suspend fun findByEmail(email: String): OtpRecord?
    suspend fun delete(email: String)
}