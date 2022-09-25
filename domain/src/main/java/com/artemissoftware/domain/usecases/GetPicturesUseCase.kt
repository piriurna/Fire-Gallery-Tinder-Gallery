package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.repositories.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPicturesUseCase @Inject constructor(private val galleryRepository: GalleryRepository) {

    operator fun invoke(galleryId: Int): Flow<Resource<List<Picture>>> = flow {

        emit(Resource.Loading())

        val pictures = galleryRepository.getPictures(galleryId = galleryId)

        if(pictures.isEmpty()){
            emit(Resource.Error(message = NO_PICTURES_AVAILABLE, data = pictures))
        }
        else{
            emit(Resource.Success(data = pictures))
        }

    }

    companion object{
        const val NO_PICTURES_AVAILABLE = "No pictures available"
    }
}
