package com.artemissoftware.data.errors

class FireGalleryException (
    val code: Int? = -1,
    override val message: String?,
    val description: String? = "FirebaseError",
): RuntimeException()