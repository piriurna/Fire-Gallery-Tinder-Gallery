package com.artemissoftware.firegallery.screens.picturedetail.models

import com.artemissoftware.common.models.Chip

data class PictureUI(
    val id: String,
    val imageUrl: String,
    val isFavorite: Boolean = false,
    val tags: List<Chip> = emptyList()
)
