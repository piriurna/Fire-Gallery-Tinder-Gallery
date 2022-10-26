package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.repositories.AppSettingsDataStoreRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import com.artemissoftware.domain.repositories.RemoteConfigRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPictureDetailUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val dataStoreRepository: AppSettingsDataStoreRepository,
    private val remoteConfigRepository: RemoteConfigRepository
) {

    operator fun invoke(pictureId: String): Flow<Resource<Picture>> = flow {

        emit(Resource.Loading())
        delay(500)

        if(pictureId.isEmpty()){
            emit(Resource.Error(message = PICTURE_UNAVAILABLE))
        }
        else {

            val profile = dataStoreRepository.getAppSettings().first()
            val picture = galleryRepository.getPictureDetail(pictureId = pictureId)

            picture?.let {
                it.isFavorite = profile.favorites.contains(it.id)
                it.chipColorConfig = remoteConfigRepository.getSeasonConfigs().chipColor
                emit(Resource.Success(data = it))
            } ?: kotlin.run {
                emit(Resource.Error(message = PICTURE_UNAVAILABLE))
            }

        }
    }

    companion object{
        const val PICTURE_UNAVAILABLE = "Picture unavailable"
    }
}