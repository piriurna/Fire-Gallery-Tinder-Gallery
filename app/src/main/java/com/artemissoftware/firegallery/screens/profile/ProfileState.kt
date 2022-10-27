package com.artemissoftware.firegallery.screens.profile

import com.artemissoftware.domain.models.profile.AppConfig
import com.artemissoftware.domain.models.profile.User

data class ProfileState(
    val appConfig: AppConfig = AppConfig(),
    val user: User? = null,
    val isLoading: Boolean = false,
)
