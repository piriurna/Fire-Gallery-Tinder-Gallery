package com.artemissoftware.data.repositories

import android.content.Context
import com.artemissoftware.data.local.util.profileStore
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreRepositoryImpl(private val context: Context) : DataStoreRepository {

    override suspend fun saveProfile(profile: Profile) {
        context.profileStore.updateData {
            it.copy(
                notifications = profile.notifications,
            )
        }
    }

    override suspend fun updateFavorite(pictureId : String, isFavorite: Boolean) {
        context.profileStore.updateData {

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
        context.profileStore.updateData {

            it.copy(
                firebaseToken = firebaseToken,
            )
        }
    }


    override suspend fun getProfile(): Flow<Profile> {
        return context.profileStore.data
    }

    override fun getProfile_(): Flow<Profile> {
        return context.profileStore.data
    }
}