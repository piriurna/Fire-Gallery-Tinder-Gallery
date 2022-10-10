package com.artemissoftware.firegallery.screens.picturedetail.mappers

import com.artemissoftware.common.models.Chip
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.models.configurations.ChipColorConfig
import com.artemissoftware.firegallery.screens.picturedetail.models.PictureUI

fun Picture.toUI(): PictureUI{

    val tags = this.tags.map { this.chipColorConfig?.toChip(it) ?: Chip(it) }

    return PictureUI(
        id = id,
        imageUrl = imageUrl,
        isFavorite = isFavorite,
        tags = tags
    )
}

fun ChipColorConfig.toChip(text: String): Chip{

    return Chip(
        text = text,
        startBorderColor = startBorderColor,
        endBorderColor = endBorderColor,
        iconColor = iconColor
    )

}