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

    override suspend fun getProfile(): Flow<Profile> {
        return context.profileStore.data
    }
}