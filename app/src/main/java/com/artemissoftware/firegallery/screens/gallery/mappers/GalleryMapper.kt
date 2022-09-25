package com.artemissoftware.firegallery.screens.gallery.mappers

import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.firegallery.screens.gallery.models.GalleryUI

fun Gallery.toUI(): GalleryUI{
    return GalleryUI(name = name)
}