package com.artemissoftware.data.local.models

import kotlinx.serialization.Serializable


@Serializable
data class AppSettings(
    val notifications: Boolean = false,
    val favorites: List<String> = emptyList(),
    val firebaseToken: String? = null

)
