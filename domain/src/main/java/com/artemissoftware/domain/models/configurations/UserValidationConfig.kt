package com.artemissoftware.domain.models.configurations

data class UserValidationConfig(
    val emailRegex: String,
    val passwordMaxLength: Int,
    val passwordMinLength: Int,
)
