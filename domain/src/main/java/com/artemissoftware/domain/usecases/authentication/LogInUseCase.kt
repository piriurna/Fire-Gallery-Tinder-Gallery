package com.artemissoftware.domain.usecases.authentication

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.repositories.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {

    operator fun invoke(email: String, password: String): Flow<Resource<Any>> = flow {

        emit(Resource.Loading())

        val result = authenticationRepository.authenticateUser(email, password)

        result.data?.let {
            emit(Resource.Success())
        } ?: kotlin.run {
            emit(Resource.Error(message = result.error.message))
        }
    }

}