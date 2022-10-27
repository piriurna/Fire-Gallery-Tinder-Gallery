package com.artemissoftware.firegallery.screens.gallery.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GalleryUI(
    val id: Int,
    val name: String
) : Parcelable
