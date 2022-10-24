package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.repositories.AuthenticationRepository
import com.artemissoftware.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    operator fun invoke() = dataStoreRepository.getProfile().map {
        it.user = authenticationRepository.getUser() //Todo: não me parece bem
        it
    }
}