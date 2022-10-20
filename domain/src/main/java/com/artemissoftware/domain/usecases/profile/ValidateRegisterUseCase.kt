package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.models.profile.RegisterUserValidation
import com.artemissoftware.domain.repositories.RemoteConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateRegisterUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository
) {

    operator fun invoke(email: String, password: String, passwordConfirm: String): Flow<Resource<RegisterUserValidation>> = flow {

        val registerUserValidation = RegisterUserValidation()
        var validEmail = false
        var validPassword = false

        if(isEmailValid(email)){
            validEmail = true
        }
        else{
            registerUserValidation.emailError = INVALID_EMAIL
        }

        if(validatePassword(password, passwordConfirm)){
            validPassword = true
        }
        else{
            registerUserValidation.passwordError = INVALID_PASSWORD
        }

        registerUserValidation.isValid = validEmail && validPassword
        emit(Resource.Success(data = registerUserValidation))
    }



    private fun validatePassword(password: String, passwordConfirm: String) : Boolean{

        if (password.isEmpty() || password.isBlank()) {
            return false
        }

        if (password.trim().length < 3 || password.length > 20) {
            return false
        }

        if(password != passwordConfirm){
            return false
        }

        return true

    }

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }

    companion object{

        const val INVALID_EMAIL = "Invalid email"
        const val INVALID_PASSWORD = "Invalid password"
    }
}