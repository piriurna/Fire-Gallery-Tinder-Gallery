package com.artemissoftware.data.local.models

import kotlinx.serialization.Serializable


@Serializable
data class ProfileSettings(
    val notifications: Boolean = false,
    val favorites: List<String> = emptyList(),
    val firebaseToken: String? = null

)
