package com.artemissoftware.firegallery.screens.profile

import com.artemissoftware.domain.models.profile.Profile

data class ProfileState(
    val profile: Profile = Profile(),
    val isLoading: Boolean = false,
)
