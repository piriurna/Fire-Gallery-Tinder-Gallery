package com.artemissoftware.data.mappers

import com.artemissoftware.data.firebase.entities.GalleryFso
import com.artemissoftware.data.firebase.entities.PictureFso
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.domain.models.Picture

fun GalleryFso.toGallery(): Gallery {

    return Gallery(
        id = id,
        name = name,
        imageUrl = imageUrl,
    )
}


fun PictureFso.toPicture(): Picture {

    return Picture(
        id = id,
        imageUrl = url,
    )
}