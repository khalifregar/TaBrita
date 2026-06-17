package tech.tabrita.backend.repository

import tech.tabrita.backend.model.OtpRecord
import java.util.concurrent.ConcurrentHashMap

class InMemoryOtpRepository : OtpRepository {
    private val otps = ConcurrentHashMap<String, OtpRecord>()

    override suspend fun save(otpRecord: OtpRecord) {
        otps[otpRecord.email.lowercase()] = otpRecord
    }

    override suspend fun findByEmail(email: String): OtpRecord? {
        val record = otps[email.lowercase()] ?: return null
        if (record.expiresAt.isBefore(java.time.Instant.now())) {
            otps.remove(email.lowercase())
            return null
        }
        return record
    }

    override suspend fun delete(email: String) {
        otps.remove(email.lowercase())
    }
}