package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.UserFavoriteImages
import com.artemissoftware.domain.models.profile.AppConfig
import kotlinx.coroutines.flow.Flow

interface ProfileDataStoreRepository {

    suspend fun updateFavorite(pictureId : String, email: String, isFavorite: Boolean)

    fun getProfile(): Flow<UserFavoriteImages>
}