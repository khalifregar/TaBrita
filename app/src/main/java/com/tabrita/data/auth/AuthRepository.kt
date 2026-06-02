package com.tabrita.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

data class AppUser(
    val uid: String,
    val email: String?,
    val displayName: String?,
    val photoUrl: String?,
    val isAdmin: Boolean = false
)

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    val currentUser get() = auth.currentUser

    suspend fun signInWithGoogle(idToken: String): Result<AppUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val firebaseUser = result.user ?: return Result.failure(Exception("No user"))

            // Check or create user doc for role
            val userDoc = firestore.collection("users").document(firebaseUser.uid).get().await()
            val isAdmin = if (userDoc.exists()) {
                userDoc.getBoolean("isAdmin") ?: false
            } else {
                // For demo: mark as admin if email contains "admin" or specific
                val adminEmails = listOf("admin@tabrita.com", "khalifregar@gmail.com") // TODO: replace with your admin emails
                val isAdminEmail = firebaseUser.email?.let { email ->
                    adminEmails.any { it.equals(email, ignoreCase = true) } || email.contains("admin", ignoreCase = true)
                } ?: false

                // Write user doc
                val userData = mapOf(
                    "email" to firebaseUser.email,
                    "displayName" to firebaseUser.displayName,
                    "photoUrl" to firebaseUser.photoUrl?.toString(),
                    "isAdmin" to isAdminEmail,
                    "createdAt" to System.currentTimeMillis()
                )
                firestore.collection("users").document(firebaseUser.uid).set(userData).await()
                isAdminEmail
            }

            Result.success(
                AppUser(
                    uid = firebaseUser.uid,
                    email = firebaseUser.email,
                    displayName = firebaseUser.displayName,
                    photoUrl = firebaseUser.photoUrl?.toString(),
                    isAdmin = isAdmin
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun getCurrentAppUser(): AppUser? {
        val firebaseUser = auth.currentUser ?: return null
        val userDoc = firestore.collection("users").document(firebaseUser.uid).get().await()
        val isAdmin = userDoc.getBoolean("isAdmin") ?: false
        return AppUser(
            uid = firebaseUser.uid,
            email = firebaseUser.email,
            displayName = firebaseUser.displayName,
            photoUrl = firebaseUser.photoUrl?.toString(),
            isAdmin = isAdmin
        )
    }
}
