package com.artemissoftware.data.firebase.cloudstore.source

import com.artemissoftware.data.errors.FireGalleryException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthenticationSource @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    suspend fun loginUser(email: String, password: String) : FirebaseUser {

        return suspendCoroutine { continuation ->
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { task->
                    task.user?.let {
                        continuation.resume(it)
                    } ?: kotlin.run {
                        continuation.resumeWithException(FireGalleryException(message = "Error authenticating the user"))
                    }
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    fun getUser() : FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun logOut() {
        return firebaseAuth.signOut()
    }
}

