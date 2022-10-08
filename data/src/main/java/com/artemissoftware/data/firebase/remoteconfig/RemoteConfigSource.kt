package com.artemissoftware.data.firebase.remoteconfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RemoteConfigSource @Inject constructor (private val firebaseRemoteConfig: FirebaseRemoteConfig) {

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

    fun getString(key: String): String = firebaseRemoteConfig.getString(key)

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