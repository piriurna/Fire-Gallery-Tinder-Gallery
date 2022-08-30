package com.artemissoftware.data.mappers

import com.artemissoftware.domain.FirebaseError
import com.google.firebase.firestore.FirebaseFirestoreException

fun FirebaseFirestoreException.toFirebaseError() = FirebaseError(
    code =  "${this.code.value()}  ${this.code.name}",
    message = this.message ?: "FirebaseError"
)