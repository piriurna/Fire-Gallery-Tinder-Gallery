package com.artemissoftware.domain.models.profile

data class Profile(
    val notifications: Boolean = false,
    val favorites: List<String> = emptyList()
)
