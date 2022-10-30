package com.artemissoftware.domain.usecases.favorite

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.repositories.AuthenticationRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import com.artemissoftware.domain.repositories.ProfileDataStoreRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoritePicturesUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val profileDataStoreRepository: ProfileDataStoreRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    operator fun invoke() = profileDataStoreRepository.getUserProfile().combine( authenticationRepository.getUser()) { userLocalData, user ->

        val favorites = userLocalData.data[user?.email]

        favorites?.let{

            val result = galleryRepository.getFavoritePictures(it)

            result.data?.let { pictures->

                if (pictures.isEmpty()) {
                    Resource.Error(message = NO_FAVORITE_PICTURES_AVAILABLE, data = pictures)
                } else {
                    Resource.Success(data = pictures)
                }

            } ?: kotlin.run {
                Resource.Error(message = NO_FAVORITE_PICTURES_AVAILABLE)
            }

        } ?: kotlin.run {
            Resource.Error(message = NO_FAVORITE_PICTURES_AVAILABLE)
        }
    }

    companion object{
        const val NO_FAVORITE_PICTURES_AVAILABLE = "No favorite pictures available"
    }
}
