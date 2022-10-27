package com.artemissoftware.firegallery.screens.picturedetail

import com.artemissoftware.domain.models.Picture

data class PictureDetailState(
    val picture: Picture? = null,
    val isLoading: Boolean = false
)
