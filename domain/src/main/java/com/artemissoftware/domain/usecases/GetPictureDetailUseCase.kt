package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.repositories.DataStoreRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import com.artemissoftware.domain.repositories.RemoteConfigRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPictureDetailUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val remoteConfigRepository: RemoteConfigRepository
) {

    operator fun invoke(pictureId: String): Flow<Resource<Picture>> = flow {

        emit(Resource.Loading())
        delay(1000)

        val profile = dataStoreRepository.getProfile().first()
        val picture = galleryRepository.getPictureDetail(pictureId = pictureId)
        picture.isFavorite = profile.favorites.contains(picture.id)
        picture.chipColorConfig = remoteConfigRepository.getSeasonConfigs().chipColor
        emit(Resource.Success(data = picture))
    }

}