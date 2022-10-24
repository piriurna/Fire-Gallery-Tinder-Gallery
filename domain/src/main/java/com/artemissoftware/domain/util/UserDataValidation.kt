package com.artemissoftware.domain.util

import com.artemissoftware.domain.models.configurations.UserValidationConfig

object UserDataValidation {


    fun isEmailValid(emailRegex: String, email: String): Boolean {
        return emailRegex.toRegex().matches(email);
    }

    fun validateUsername(username: String, userValidationConfigs: UserValidationConfig) : Boolean{

        if (username.isEmpty() || username.isBlank()) {
            return false
        }

        if (username.trim().length < userValidationConfigs.passwordMinLength || username.length > userValidationConfigs.passwordMaxLength) {
            return false
        }

        return true
    }

    fun validatePassword(password: String, userValidationConfigs: UserValidationConfig) : Boolean{

        if (password.isEmpty() || password.isBlank()) {
            return false
        }

        if (password.trim().length < userValidationConfigs.usernameMinLength || password.length > userValidationConfigs.usernameMaxLength) {
            return false
        }

        return true
    }


    fun validatePasswordConfirmation(
        password: String,
        passwordConfirm: String,
        userValidationConfigs: UserValidationConfig
    ) : Boolean{

        if (!validatePassword(password, userValidationConfigs)) {
            return false
        }

        if(password != passwordConfirm){
            return false
        }

        return true

    }
}