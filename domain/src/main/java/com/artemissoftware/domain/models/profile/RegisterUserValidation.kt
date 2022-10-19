package com.artemissoftware.domain.models.profile

data class RegisterUserValidation(
    var isValid: Boolean = false,
    var emailError: String = "",
    var passwordError: String = "",
    var passwordConfirmError: String = ""
)
