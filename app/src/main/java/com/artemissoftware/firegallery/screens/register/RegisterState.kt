package com.artemissoftware.firegallery.screens.register

import com.artemissoftware.domain.models.configurations.UserValidationConfig

data class RegisterState (
    val isValidData: Boolean = false,
    val validationRules: UserValidationConfig? = null,
    val registered: Boolean = false,
    val isLoading: Boolean = false
)