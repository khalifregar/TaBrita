# TaBrita Auth API (Ktor Backend)

This is the Ktor-based authentication backend for TaBrita, integrated as a Gradle subproject in the monorepo.

## Features
- Register user (email, password, name)
- Login (returns JWT)
- OTP via Email (simulated - printed to console)
- Verify OTP
- Resend OTP
- Protected route example (/auth/me)

## Architecture (as requested)
- **DTOs**: request/ (RegisterRequest, LoginRequest, VerifyOtpRequest, ResendOtpRequest), response/ (AuthResponse, MessageResponse, UserResponse)
- **Models**: User, OtpRecord
- **Repository Layer**: UserRepository, OtpRepository (in-memory implementations for demo)
- **Service Layer**: AuthService, OtpService (business logic, OTP generation, password hash, JWT)
- **Security**: PasswordHasher (BCrypt), JwtService
- **Routes**: authRoutes with Ktor routing

## How to Run

From the TaBrita root:

```bash
./gradlew :backend:run
```

The server starts on http://localhost:9090 (to avoid common port conflicts like Tomcat on 8080)

## API Endpoints

### Register
POST /auth/register
```json
{
  "email": "user@example.com",
  "password": "secret123",
  "name": "John Doe"
}
```
Response: 201 - OTP is printed in server console.

### Verify OTP
POST /auth/verify-otp
```json
{
  "email": "user@example.com",
  "otp": "123456"
}
```

### Resend OTP
POST /auth/resend-otp
```json
{
  "email": "user@example.com"
}
```

### Login
POST /auth/login
```json
{
  "email": "user@example.com",
  "password": "secret123"
}
```
Returns JWT token + user info.

### Protected
GET /auth/me
Header: Authorization: Bearer <token>

## Notes
- Currently uses **in-memory storage** (resets on restart). Replace InMemory*Repository with real DB (Exposed + Postgres etc.) for production.
- Email OTP is **simulated** (prints to console/log). Integrate real email provider (JavaMail, AWS SES, etc.) in OtpService.
- JWT secret is hardcoded for demo. Use environment variables in production.
- Passwords are BCrypt hashed.

## Monorepo
Added as `:backend` module in TaBrita/settings.gradle.kts.
Root build supports Kotlin JVM for the backend.

Run full project build: `./gradlew build`

## Future Improvements
- Real database (Exposed)
- Real email sending
- Rate limiting
- Refresh tokens
- Email verification link instead of OTP (optional)
- Unit tests

Built with Ktor + Kotlin + Repository + Service + DTO pattern.