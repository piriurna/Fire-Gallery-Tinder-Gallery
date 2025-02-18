package com.artemissoftware.firegallery.screens.register

import com.artemissoftware.firegallery.ui.FGBaseEvents

sealed class RegisterEvents: FGBaseEvents() {
    data class ValidateRegister(val email: String, val password: String, val passwordConfirm: String, val username: String): RegisterEvents()
    data class Register(val email: String, val password: String, val username: String): RegisterEvents()
    object GetValidationRules : RegisterEvents()
}
