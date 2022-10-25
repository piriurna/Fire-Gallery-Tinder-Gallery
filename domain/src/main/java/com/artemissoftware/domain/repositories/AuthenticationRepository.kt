package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.FirebaseResponse
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.models.profile.User

interface AuthenticationRepository {

    suspend fun authenticateUser(email: String, password: String): FirebaseResponse<Boolean>
    suspend fun registerUser(email: String, password: String, username: String): FirebaseResponse<Boolean>

    fun getUser(): User?

    fun logOut()
}