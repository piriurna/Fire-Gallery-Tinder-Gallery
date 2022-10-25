package com.artemissoftware.data.repositories

import com.artemissoftware.data.errors.FireGalleryException
import com.artemissoftware.data.firebase.HandleFirebase
import com.artemissoftware.data.firebase.cloudstore.source.AuthenticationSource
import com.artemissoftware.data.firebase.entities.PictureFso
import com.artemissoftware.data.mappers.toFirebaseError
import com.artemissoftware.data.mappers.toUser
import com.artemissoftware.domain.FirebaseResponse
import com.artemissoftware.domain.models.profile.User
import com.artemissoftware.domain.repositories.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepositoryImpl(
    private val authenticationSource: AuthenticationSource
) : AuthenticationRepository {


    override suspend fun authenticateUser(email: String, password: String): FirebaseResponse<Boolean> {

        return try {

            //TODO: pictureFso deve ser alterado
            val response = HandleFirebase.safeApiCall<FirebaseUser, PictureFso>{ authenticationSource.loginUser(email, password) }

            return FirebaseResponse(data = true)

        } catch (ex: FireGalleryException) {
            FirebaseResponse(error = ex.toFirebaseError())
        }
    }

    override suspend fun registerUser(email: String, password: String, username: String): FirebaseResponse<Boolean> {

        return try {

            //TODO: pictureFso deve ser alterado
            val response = HandleFirebase.safeApiCall<FirebaseUser, PictureFso>{ authenticationSource.registerUser(email = email, password = password, username = username) }

            return FirebaseResponse(data = true)

        } catch (ex: FireGalleryException) {
            FirebaseResponse(error = ex.toFirebaseError())
        }
    }


    override fun getUser(): User? {
        return authenticationSource.getUser()?.toUser()
    }

    override fun logOut() {
        authenticationSource.logOut()
    }


}