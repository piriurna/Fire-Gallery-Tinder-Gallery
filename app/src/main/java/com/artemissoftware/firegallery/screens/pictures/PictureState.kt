package com.artemissoftware.firegallery.screens.pictures

import com.artemissoftware.domain.models.Picture

data class PictureState(
    val pictures: List<Picture> = emptyList(),
    val isLoading: Boolean = false,
    val showOptions: Boolean = false,
)