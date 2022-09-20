package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {

    operator fun invoke(pictureId : String, isFavorite: Boolean): Flow<Resource<Any>> = flow {

        dataStoreRepository.updateFavorite(pictureId = pictureId, isFavorite = isFavorite)
        emit(Resource.Success())
    }

}