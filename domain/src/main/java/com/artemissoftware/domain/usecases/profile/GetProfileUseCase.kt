package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.repositories.AuthenticationRepository
import com.artemissoftware.domain.repositories.AppSettingsDataStoreRepository
import com.artemissoftware.domain.repositories.ProfileDataStoreRepository
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val appSettingsDataStoreRepository: AppSettingsDataStoreRepository,
    private val profileDataStoreRepository: ProfileDataStoreRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    operator fun invoke() = combine(
        appSettingsDataStoreRepository.getAppSettings(),
        profileDataStoreRepository.getUserProfile(),
        authenticationRepository.getUser()
    ) { appSettings, userData, userInfo ->

        userInfo?.let { user ->
            userData.data[user.email]?.let { user.favorites = it }
        }
        Profile(appConfig = appSettings, user = userInfo)
    }

}