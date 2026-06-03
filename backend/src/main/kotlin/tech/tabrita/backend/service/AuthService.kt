package tech.tabrita.backend.service

import tech.tabrita.backend.dto.request.LoginRequest
import tech.tabrita.backend.dto.request.RegisterRequest
import tech.tabrita.backend.dto.response.AuthResponse
import tech.tabrita.backend.dto.response.UserResponse
import tech.tabrita.backend.model.User
import tech.tabrita.backend.repository.UserRepository
import tech.tabrita.backend.security.JwtService
import tech.tabrita.backend.security.PasswordHasher

class AuthService(
    private val userRepository: UserRepository,
    private val otpService: OtpService,
    private val jwtService: JwtService = JwtService
) {
    suspend fun register(request: RegisterRequest): AuthResponse {
        val existing = userRepository.findByEmail(request.email)
        if (existing != null) {
            throw IllegalArgumentException("Email already registered")
        }

        val passwordHash = PasswordHasher.hash(request.password)
        val user = User(
            email = request.email.lowercase(),
            passwordHash = passwordHash,
            name = request.name
        )
        userRepository.save(user)

        // Send OTP for verification
        otpService.generateAndSendOtp(user.email)

        // For demo, we can return token even if not verified, but better to require verify.
        // To match typical flow, return message instead, but since login needs verified, here return user info.
        // For simplicity in this API, issue token only after verify, but for register response use a temp or just message.
        // Let's return success message via caller.

        // Actually for this impl, register succeeds, OTP sent. Client should call verify-otp then login.
        // But to provide token immediately? No, require verify for security.
        // Return a response indicating OTP sent.

        // For now, to provide value, we'll create a temp response. But better to have separate.
        // Adjust: many APIs return 200 with "OTP sent"

        val userResponse = UserResponse(
            id = user.id,
            email = user.email,
            name = user.name,
            isVerified = user.isVerified
        )

        // Since not verified, perhaps don't give token yet.
        // But to make login work after verify, we return message.
        // The caller (route) will handle.

        // For this service, let's return the user and note that OTP is sent.
        return AuthResponse(
            token = "", // no token until verified + login
            user = userResponse
        )
    }

    suspend fun login(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("Invalid credentials")

        if (!PasswordHasher.verify(request.password, user.passwordHash)) {
            throw IllegalArgumentException("Invalid credentials")
        }

        if (!user.isVerified) {
            throw IllegalStateException("Account not verified. Please verify with OTP sent to your email.")
        }

        val token = jwtService.generateToken(user.id, user.email)

        val userResponse = UserResponse(
            id = user.id,
            email = user.email,
            name = user.name,
            isVerified = user.isVerified
        )

        return AuthResponse(token = token, user = userResponse)
    }

    suspend fun verifyOtp(email: String, otp: String): Boolean {
        val success = otpService.verifyOtp(email, otp)
        if (success) {
            userRepository.updateVerification(email, true)
            return true
        }
        return false
    }

    suspend fun resendOtp(email: String) {
        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("User not found")
        if (user.isVerified) {
            throw IllegalStateException("User already verified")
        }
        otpService.resendOtp(email)
    }
}