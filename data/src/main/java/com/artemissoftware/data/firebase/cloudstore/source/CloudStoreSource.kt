package com.artemissoftware.data.firebase.cloudstore.source

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CloudStoreSource @Inject constructor(private val firebaseFirestore: FirebaseFirestore){

    suspend fun getDocuments(path: String): List<DocumentSnapshot> {

        return suspendCoroutine { continuation ->
            firebaseFirestore.collection(path).get()
                .addOnSuccessListener {
                    continuation.resume(it.documents)
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }
}