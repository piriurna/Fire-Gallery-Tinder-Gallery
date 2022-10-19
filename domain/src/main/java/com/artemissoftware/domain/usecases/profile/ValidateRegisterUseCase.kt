package com.artemissoftware.domain.usecases.profile

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.models.profile.RegisterUserValidation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateRegisterUseCase @Inject constructor() {

    operator fun invoke(email: String, password: String, passwordConfirm: String): Flow<Resource<RegisterUserValidation>> = flow {


        val registerUserValidation = RegisterUserValidation()
        var validEmail = false

        if(isEmailValid(email)){
            validEmail = true
        }
        else{
            registerUserValidation.emailError
        }

        registerUserValidation.isValid = validEmail
        emit(Resource.Success(data = registerUserValidation))
    }



//    private fun validateEmail(email: String) : Boolean{
//
//        if (email.isEmpty() || email.isBlank()) {
//            errors.add(ValidateFieldException.Error.EMPTY)
//        }
//
//        if (nickname.trim().length < 3 || nickname.length > 20) {
//            errors.add(ValidateFieldException.Error.INVALID)
//        }
//
//
//    }

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }
}