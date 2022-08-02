package com.artemissoftware.data.mappers

import com.artemissoftware.data.firebase.entities.GalleryFso
import com.artemissoftware.domain.models.Gallery

fun GalleryFso.toGallery(): Gallery {

    return Gallery(
        id = id,
        name = name,
        imageUrl = imageUrl,
    )
}