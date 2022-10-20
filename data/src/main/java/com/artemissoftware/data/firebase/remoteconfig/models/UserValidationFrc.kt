package com.artemissoftware.data.firebase.remoteconfig.models

data class UserValidationFrc(
    val emailRegex: String,
    val passwordMaxLength: Int,
    val passwordMinLength: Int
)
