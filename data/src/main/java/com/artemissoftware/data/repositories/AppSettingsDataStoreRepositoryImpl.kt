package com.artemissoftware.data.repositories

import android.content.Context
import com.artemissoftware.data.local.util.appSettingsStore
import com.artemissoftware.domain.models.profile.AppConfig
import com.artemissoftware.domain.repositories.AppSettingsDataStoreRepository
import kotlinx.coroutines.flow.Flow

class AppSettingsDataStoreRepositoryImpl(private val context: Context) : AppSettingsDataStoreRepository {

    override suspend fun saveAppSettings(appConfig: AppConfig) {
        context.appSettingsStore.updateData {
            it.copy(
                notifications = appConfig.notifications,
            )
        }
    }

    override suspend fun updateFavorite(pictureId : String, isFavorite: Boolean) {
        context.appSettingsStore.updateData {

            var favorites = it.favorites.toMutableList()
            if(isFavorite){
                favorites.add(pictureId)
            }
            else{
                favorites.remove(pictureId)
            }

            it.copy(
                favorites = favorites,
            )
        }
    }

    override suspend fun updateFirebaseToken(firebaseToken: String) {
        context.appSettingsStore.updateData {

            it.copy(
                firebaseToken = firebaseToken,
            )
        }
    }


    override fun getAppSettings(): Flow<AppConfig> {
        return context.appSettingsStore.data
    }
}