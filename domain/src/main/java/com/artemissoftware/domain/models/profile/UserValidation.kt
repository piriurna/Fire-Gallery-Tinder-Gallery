package com.artemissoftware.domain.models.profile

data class UserValidation(
    var isValid: Boolean = false,
    var emailError: String = "",
    var passwordError: String = "",
    var passwordConfirmError: String = "",
    var usernameError: String = "",
)
