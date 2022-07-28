package com.artemissoftware.data.repositories

import com.artemissoftware.data.firebase.cloudstore.source.CloudStoreSource
import com.artemissoftware.data.firebase.entities.GalleryFso
import com.artemissoftware.data.mappers.toGallery
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.domain.repositories.GalleryRepository
import com.google.firebase.firestore.ktx.toObject

class GalleryRepositoryImpl(
    private val cloudStoreSource: CloudStoreSource
) : GalleryRepository {

    override suspend fun getGalleries(): List<Gallery> {

        return cloudStoreSource.getDocuments("gallery").map { document ->
            document.toObject<GalleryFso>()!!.toGallery()
        }
    }
}