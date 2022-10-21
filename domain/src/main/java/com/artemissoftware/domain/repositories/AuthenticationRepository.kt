package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.FirebaseResponse
import com.artemissoftware.domain.models.Picture

interface AuthenticationRepository {

    suspend fun authenticateUser(email: String, password: String): FirebaseResponse<Boolean>
}