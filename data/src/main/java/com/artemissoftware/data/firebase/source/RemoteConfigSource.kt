package com.artemissoftware.data.firebase.source

import com.artemissoftware.data.firebase.remoteconfig.models.SeasonFrc
import com.artemissoftware.data.firebase.remoteconfig.models.UserValidationFrc
import com.artemissoftware.data.mappers.toSeasonConfig
import com.artemissoftware.data.mappers.toUserValidationConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RemoteConfigSource @Inject constructor (private val firebaseRemoteConfig: FirebaseRemoteConfig) {

    lateinit var seasonConfig: SeasonFrc
    lateinit var userValidationConfig: UserValidationFrc

    suspend fun fetchValues(): Boolean = suspendCoroutine { continuation ->
        setupFirebaseRemoteConfig()

        firebaseRemoteConfig.fetchAndActivate()
            .addOnSuccessListener {
                loadConfigurations()
                continuation.resume(it)
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }

    private fun loadConfigurations() {
        with(firebaseRemoteConfig){

            seasonConfig = toSeasonConfig()
            userValidationConfig = toUserValidationConfig()
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