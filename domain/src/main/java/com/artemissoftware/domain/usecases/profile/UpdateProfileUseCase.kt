package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {

    operator fun invoke(profile : Profile): Flow<Resource<Any>> = flow {

        dataStoreRepository.saveProfile(profile)
        emit(Resource.Success())
    }

}