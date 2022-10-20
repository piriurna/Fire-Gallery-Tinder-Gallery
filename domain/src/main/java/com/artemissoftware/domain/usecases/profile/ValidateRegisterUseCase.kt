package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.UserValidation
import com.artemissoftware.domain.repositories.RemoteConfigRepository
import com.artemissoftware.domain.util.UserDataValidation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateRegisterUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository
) {

    operator fun invoke(email: String, password: String, passwordConfirm: String): Flow<Resource<UserValidation>> = flow {

        val userValidation = UserValidation()
        var validEmail = false
        var validPassword = false

        if(UserDataValidation.isEmailValid(remoteConfigRepository.getUserValidationConfigs().emailRegex, email)){
            validEmail = true
        }
        else{
            userValidation.emailError = ValidateLoginUseCase.INVALID_EMAIL
        }

        if(UserDataValidation.validatePasswordConfirmation(password, passwordConfirm, remoteConfigRepository.getUserValidationConfigs())){
            validPassword = true
        }
        else{
            userValidation.passwordError = ValidateLoginUseCase.INVALID_PASSWORD
        }

        userValidation.isValid = validEmail && validPassword
        emit(Resource.Success(data = userValidation))
    }


    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";


    companion object{

        const val INVALID_EMAIL = "Invalid email"
        const val INVALID_PASSWORD = "Invalid password"
    }
}