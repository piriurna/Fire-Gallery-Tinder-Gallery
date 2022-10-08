package com.artemissoftware.data.firebase.remoteconfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RemoteConfigSource(private val firebaseRemoteConfig: FirebaseRemoteConfig) {

    suspend fun fetchValues(): Boolean = suspendCoroutine { continuation ->
        setupFirebaseRemoteConfig()

        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener { fetchTask ->
            if (fetchTask.isSuccessful) {

                continuation.resume(true)
            } else {
                continuation.resume(false)
            }
        }
    }


    private fun setupFirebaseRemoteConfig() {
        firebaseRemoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = MINIMUM_FETCH_INTERVAL
            }
        )
    }

    companion object {
        const val MINIMUM_FETCH_INTERVAL = 30L
    }


}