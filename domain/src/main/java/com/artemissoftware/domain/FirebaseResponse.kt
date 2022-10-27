package com.artemissoftware.domain

data class FirebaseResponse<T>(
    val data: T? = null,
    val error: FirebaseError = FirebaseError()
)