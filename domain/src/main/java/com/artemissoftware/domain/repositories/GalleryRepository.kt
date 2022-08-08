package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.domain.models.Picture

interface GalleryRepository {

    suspend fun getGalleries(): List<Gallery>

    suspend fun getPictures(galleryId: Int): List<Picture>

    suspend fun getPictureDetail(pictureId: String): Picture
}