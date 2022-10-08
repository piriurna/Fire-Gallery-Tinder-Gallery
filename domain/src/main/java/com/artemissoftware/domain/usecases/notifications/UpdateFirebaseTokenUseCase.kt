package com.artemissoftware.domain.usecases.notifications

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateFirebaseTokenUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {

    operator fun invoke(firebaseToken : String): Flow<Resource<Any>> = flow {

        dataStoreRepository.updateFirebaseToken(firebaseToken = firebaseToken)
        emit(Resource.Success())
    }

}