package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.AppConfig
import com.artemissoftware.domain.repositories.AppSettingsDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(private val dataStoreRepository: AppSettingsDataStoreRepository) {

    operator fun invoke(appConfig : AppConfig): Flow<Resource<Any>> = flow {

        dataStoreRepository.saveAppSettings(appConfig)
        emit(Resource.Success())
    }

}