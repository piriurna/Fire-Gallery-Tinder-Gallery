package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.models.configurations.UserValidationConfig
import com.artemissoftware.domain.repositories.RemoteConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetValidationRulesUseCase @Inject constructor(private val remoteConfigRepository: RemoteConfigRepository) {

    operator fun invoke(): Flow<UserValidationConfig> = flow {
        remoteConfigRepository.getUserValidationConfigs()
    }

}