package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.repositories.DataStoreRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPictureDetailUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val dataStoreRepository: DataStoreRepository
) {

    operator fun invoke(pictureId: String): Flow<Resource<Picture>> = flow {

        emit(Resource.Loading())
        delay(2000)

        val picture = galleryRepository.getPictureDetail(pictureId = pictureId)
        picture.isFavorite = dataStoreRepository.getProfile().first().favorites.contains(picture.id)
        emit(Resource.Success(data = picture))
    }

}