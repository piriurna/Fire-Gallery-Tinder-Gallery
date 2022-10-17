package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.profile.Profile
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveProfile(profile: Profile)

    suspend fun updateFavorite(pictureId : String, isFavorite: Boolean)
    suspend fun updateFirebaseToken(firebaseToken : String)

    suspend fun getProfile(): Flow<Profile>
    fun getProfile_(): Flow<Profile>
}