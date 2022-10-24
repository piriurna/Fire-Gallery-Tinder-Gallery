package com.artemissoftware.firegallery.screens.profile

import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.models.profile.User

data class ProfileState(
    val profile: Profile = Profile(),
    val user: User? = null,
    val isLoading: Boolean = false,
)
