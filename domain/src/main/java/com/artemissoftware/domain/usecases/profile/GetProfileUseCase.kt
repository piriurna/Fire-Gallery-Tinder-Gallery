package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.repositories.DataStoreRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    operator fun invoke() = dataStoreRepository.getProfile()
}