package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.profile.AppConfig
import kotlinx.coroutines.flow.Flow

interface AppSettingsDataStoreRepository {

    suspend fun saveAppSettings(appConfig: AppConfig)

    suspend fun updateFavorite(pictureId : String, isFavorite: Boolean)
    suspend fun updateFirebaseToken(firebaseToken : String)

    fun getAppSettings(): Flow<AppConfig>
}