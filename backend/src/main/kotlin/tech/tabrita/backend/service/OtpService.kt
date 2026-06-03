package tech.tabrita.backend.service

import tech.tabrita.backend.model.OtpRecord
import tech.tabrita.backend.repository.OtpRepository
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.random.Random

class OtpService(
    private val otpRepository: OtpRepository
) {
    private val OTP_EXPIRY_MINUTES = 5L

    suspend fun generateAndSendOtp(email: String): String {
        val otp = generateOtp()
        val expiresAt = Instant.now().plus(OTP_EXPIRY_MINUTES, ChronoUnit.MINUTES)
        val record = OtpRecord(email = email, otp = otp, expiresAt = expiresAt)
        otpRepository.save(record)

        // Simulate sending email - in real app use JavaMail or SendGrid etc.
        println("=== [OTP EMAIL SIMULATION] ===")
        println("To: $email")
        println("Subject: Your TaBrita OTP Code")
        println("Your OTP code is: $otp")
        println("It will expire in $OTP_EXPIRY_MINUTES minutes.")
        println("================================")

        return otp
    }

    suspend fun verifyOtp(email: String, otp: String): Boolean {
        val record = otpRepository.findByEmail(email) ?: return false
        if (record.otp == otp) {
            otpRepository.delete(email)
            return true
        }
        return false
    }

    suspend fun resendOtp(email: String) {
        // Invalidate old
        otpRepository.delete(email)
        generateAndSendOtp(email)
    }

    private fun generateOtp(): String {
        return (100000 + Random.nextInt(900000)).toString() // 6 digit
    }
}