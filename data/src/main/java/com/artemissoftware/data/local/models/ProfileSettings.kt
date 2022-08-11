package com.artemissoftware.data.local.models

import kotlinx.serialization.Serializable


@Serializable
data class ProfileSettings(
    val notifications: Boolean = false
)
