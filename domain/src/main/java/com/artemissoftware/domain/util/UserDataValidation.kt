package com.artemissoftware.domain.util

object UserDataValidation {


    fun isEmailValid(emailRegex: String, email: String): Boolean {
        return emailRegex.toRegex().matches(email);
    }

    fun validatePassword(password: String) : Boolean{

        if (password.isEmpty() || password.isBlank()) {
            return false
        }

        if (password.trim().length < 3 || password.length > 20) {
            return false
        }

        return true

    }


    fun validatePasswordConfirmation(password: String, passwordConfirm: String) : Boolean{

        if (!validatePassword(password)) {
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
}