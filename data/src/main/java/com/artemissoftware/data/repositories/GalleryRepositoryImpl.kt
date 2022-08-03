package com.artemissoftware.data.repositories

import com.artemissoftware.data.firebase.FireStoreCollection
import com.artemissoftware.data.firebase.cloudstore.source.CloudStoreSource
import com.artemissoftware.data.firebase.entities.GalleryFso
import com.artemissoftware.data.firebase.entities.PictureFso
import com.artemissoftware.data.mappers.toGallery
import com.artemissoftware.data.mappers.toPicture
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.repositories.GalleryRepository
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val cloudStoreSource: CloudStoreSource
) : GalleryRepository {

    override suspend fun getGalleries(): List<Gallery> {

        return cloudStoreSource.getDocuments(collectionName = FireStoreCollection.GALLERY).map { document ->
            document.toObject<GalleryFso>()!!.toGallery()
        }
    }

    override suspend fun getPictures(galleryId: String): List<Picture> {

        return cloudStoreSource.getPictures(galleryId).map { document ->
            document.toObject<PictureFso>()!!.toPicture()
        }
    }
}