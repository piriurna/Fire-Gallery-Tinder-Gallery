package com.artemissoftware.domain.models.configurations

data class UserValidationConfig(
    val emailRegex: String,
    val usernameRegex: String,
    val passwordMaxLength: Int,
    val passwordMinLength: Int,
    val usernameMaxLength: Int,
    val usernameMinLength: Int,
)
