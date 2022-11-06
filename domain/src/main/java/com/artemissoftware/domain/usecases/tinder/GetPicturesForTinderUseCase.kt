package com.artemissoftware.domain.usecases.tinder

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.repositories.AuthenticationRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import com.artemissoftware.domain.repositories.ProfileDataStoreRepository
import com.artemissoftware.domain.usecases.favorite.GetFavoritePicturesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPicturesForTinderUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val profileDataStoreRepository: ProfileDataStoreRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    operator fun invoke(numberOfImages : Int = DEFAULT_NUMBER_OF_IMAGES): Flow<Resource<List<Picture>>> = profileDataStoreRepository.getProfile().map { preferences ->

        val user = authenticationRepository.getUser()

        val alreadyFavoriteImages = preferences.data[user?.email]?: listOf("-1")

        val pictures = galleryRepository.getPicturesForTinder(numberOfImages = numberOfImages, favoriteImages = alreadyFavoriteImages)
        if(pictures.isEmpty()){
            Resource.Error(message = NO_PICTURES_AVAILABLE, data = pictures)
        }
        else{
            Resource.Success(data = pictures)
        }


    }

    companion object{
        const val DEFAULT_NUMBER_OF_IMAGES = 3
        const val NO_PICTURES_AVAILABLE = "No pictures available"
    }
}