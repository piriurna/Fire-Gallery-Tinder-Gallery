package com.artemissoftware.firegallery.screens.gallery

import com.artemissoftware.firegallery.ui.FGBaseEvents

sealed class GalleryEvents: FGBaseEvents() {
    object GetGalleries: GalleryEvents()
}