package com.artemissoftware.firegallery.screens.gallery

import com.artemissoftware.domain.models.Gallery

data class GalleryState(
    val galleries: List<Gallery> = listOf(),
    val isLoading: Boolean = false,
)
