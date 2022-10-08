package com.artemissoftware.domain.usecases.favorite

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.repositories.DataStoreRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritePicturesUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val dataStoreRepository: DataStoreRepository
) {

    operator fun invoke(): Flow<Resource<List<Picture>>> = flow {

        emit(Resource.Loading())

        val favorites = dataStoreRepository.getProfile().first().favorites

        if(favorites.isEmpty()){
            emit(Resource.Error(message = NO_FAVORITE_PICTURES_AVAILABLE))
        }
        else {
            val pictures = galleryRepository.getFavoritePictures(favorites)

            if (pictures.isEmpty()) {
                emit(Resource.Error(message = NO_FAVORITE_PICTURES_AVAILABLE, data = pictures))
            } else {
                emit(Resource.Success(data = pictures))
            }
        }
    }

    companion object{
        const val NO_FAVORITE_PICTURES_AVAILABLE = "No favorite pictures available"
    }
}
