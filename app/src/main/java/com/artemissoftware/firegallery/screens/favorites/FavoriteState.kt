package com.artemissoftware.firegallery.screens.favorites

import com.artemissoftware.domain.models.Picture

data class FavoriteState(
    val pictures: List<Picture> = emptyList(),
    val isLoading: Boolean = false,
)