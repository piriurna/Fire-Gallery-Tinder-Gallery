package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.Gallery

interface GalleryRepository {

    suspend fun getGalleries(): List<Gallery>

}