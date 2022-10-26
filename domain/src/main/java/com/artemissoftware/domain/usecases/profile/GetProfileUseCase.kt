package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.repositories.AuthenticationRepository
import com.artemissoftware.domain.repositories.AppSettingsDataStoreRepository
import com.artemissoftware.domain.repositories.ProfileDataStoreRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val appSettingsDataStoreRepository: AppSettingsDataStoreRepository,
    private val profileDataStoreRepository: ProfileDataStoreRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    operator fun invoke() = combine(
        appSettingsDataStoreRepository.getAppSettings(),
        profileDataStoreRepository.getProfile(),
        authenticationRepository.getUserInfo()
    ){ appSettings, profile, user ->

        profile.data[user?.name]?.let { appSettings.favorites = it}
        appSettings.user =  user
        appSettings
    }

//        appSettingsDataStoreRepository.getAppSettings().combine(profileDataStoreRepository.getProfile()) { appSettings, profile ->
//
//        val user = authenticationRepository.getUser()
//        profile.data[user?.name]?.let { appSettings.favorites = it}
//        appSettings.user =  user//Todo: não me parece bem
//        appSettings
//    }
}