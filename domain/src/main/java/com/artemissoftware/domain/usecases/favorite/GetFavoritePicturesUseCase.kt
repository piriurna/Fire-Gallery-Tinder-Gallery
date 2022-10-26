package com.artemissoftware.domain.usecases.favorite

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.repositories.AppSettingsDataStoreRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoritePicturesUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val dataStoreRepository: AppSettingsDataStoreRepository
) {

    operator fun invoke() = dataStoreRepository.getAppSettings().map { preferences ->

        val result = galleryRepository.getFavoritePictures(preferences.favorites)

        result.data?.let { pictures->

            pictures.forEach { it.isFavorite = true }

            if (pictures.isEmpty()) {
                Resource.Error(message = NO_FAVORITE_PICTURES_AVAILABLE, data = pictures)
            } else {
                Resource.Success(data = pictures)
            }

        } ?: kotlin.run {
            Resource.Error(message = NO_FAVORITE_PICTURES_AVAILABLE)
        }

    }

    companion object{
        const val NO_FAVORITE_PICTURES_AVAILABLE = "No favorite pictures available"
    }
}
