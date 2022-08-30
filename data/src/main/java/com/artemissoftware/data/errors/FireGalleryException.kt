package com.artemissoftware.data.errors

class FireGalleryException (
    val code: Int?,
    override val message: String?,
): RuntimeException()