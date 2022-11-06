package com.artemissoftware.firegallery.screens.profile

import com.artemissoftware.domain.models.profile.AppConfig
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.models.profile.User

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: Profile? = null
)
