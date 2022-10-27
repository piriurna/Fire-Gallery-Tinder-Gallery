package com.artemissoftware.data.mappers

import com.artemissoftware.data.errors.FireGalleryException
import com.artemissoftware.domain.FirebaseError
import com.google.firebase.firestore.FirebaseFirestoreException

fun FireGalleryException.toFirebaseError() = FirebaseError(
    code =  "${this.code} ${this.message}",
    message = this.description ?: ""
)

fun IllegalArgumentException.toFirebaseError() = FirebaseError(
    code =  "${this.message}",
    message = this.cause?.message ?: ""
)