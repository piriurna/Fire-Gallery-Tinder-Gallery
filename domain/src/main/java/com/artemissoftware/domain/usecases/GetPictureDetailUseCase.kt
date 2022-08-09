package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.repositories.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPictureDetailUseCase @Inject constructor(private val galleryRepository: GalleryRepository) {

    operator fun invoke(pictureId: String): Flow<Resource<Picture>> = flow {

        emit(Resource.Loading())

        val picture = galleryRepository.getPictureDetail(pictureId = pictureId)

        emit(Resource.Success(data = picture))
    }

}