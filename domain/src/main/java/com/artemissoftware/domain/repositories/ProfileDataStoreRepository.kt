package com.artemissoftware.domain.repositories

interface ProfileDataStoreRepository {

    suspend fun updateFavorite(pictureId : String, email: String, isFavorite: Boolean)
}