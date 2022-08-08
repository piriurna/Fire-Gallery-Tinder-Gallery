package com.artemissoftware.firegallery.screens.picturedetail

import com.artemissoftware.firegallery.ui.FGBaseEvents

sealed class PictureDetailEvents: FGBaseEvents() {
    data class GetPicture(val id: String): PictureDetailEvents()
}
