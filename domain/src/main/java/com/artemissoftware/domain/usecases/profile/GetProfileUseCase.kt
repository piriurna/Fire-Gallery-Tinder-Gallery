package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.repositories.AuthenticationRepository
import com.artemissoftware.domain.repositories.AppSettingsDataStoreRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val appSettingsDataStoreRepository: AppSettingsDataStoreRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    operator fun invoke() = appSettingsDataStoreRepository.getAppSettings().map {
        it.user = authenticationRepository.getUser() //Todo: não me parece bem
        it
    }
}