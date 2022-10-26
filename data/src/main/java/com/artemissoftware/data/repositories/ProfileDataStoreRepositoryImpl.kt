package com.artemissoftware.data.repositories

import android.content.Context
import com.artemissoftware.data.local.util.appSettingsStore
import com.artemissoftware.data.local.util.profileStore
import com.artemissoftware.domain.models.UserFavoriteImages
import com.artemissoftware.domain.repositories.ProfileDataStoreRepository
import kotlinx.coroutines.flow.Flow

class ProfileDataStoreRepositoryImpl(private val context: Context) : ProfileDataStoreRepository {

    override suspend fun updateFavorite(pictureId: String, email: String, isFavorite: Boolean) {
        context.profileStore.updateData {

            var favorites = it.data.toMutableMap()

            favorites[email]?.let { favorites->

                val list = favorites.toMutableList()

                if(isFavorite) {
                    list.add(pictureId)
                }
                else {
                    list.remove(pictureId)
                }
                it.data[email] = list

            } ?: kotlin.run {

                val list = mutableListOf<String>()

                if(isFavorite) {
                    list.add(pictureId)
                    favorites[email] = list
                }
            }

            it.copy(
                data = favorites as HashMap<String, List<String>>,
            )
        }
    }

    override fun getProfile(): Flow<UserFavoriteImages> {
        return context.profileStore.data
    }
}