package com.artemissoftware.firegallery.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.artemissoftware.domain.models.LocalNotification
import com.artemissoftware.domain.usecases.UpdateFirebaseTokenUseCase
import com.artemissoftware.domain.usecases.notifications.GenerateLocalNotificationUseCase
import com.artemissoftware.firegallery.MainActivity
import com.artemissoftware.firegallery.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class FGFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    internal lateinit var updateFirebaseTokenUseCase: UpdateFirebaseTokenUseCase

    @Inject
    internal lateinit var generateLocalNotificationUseCase: GenerateLocalNotificationUseCase

    private val job = SupervisorJob()

    override fun onNewToken(token: String) {
        Log.d("PushNotificationService", "Refreshed token: $token")
        saveToken(token = token)
        super.onNewToken(token)
    }


    private fun saveToken(token: String){
        updateFirebaseTokenUseCase.invoke(token).onEach {}.launchIn(
            CoroutineScope(job)
        )
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        val localNotification = LocalNotification(
            title = remoteMessage.notification?.title,
            text = remoteMessage.notification?.body
        )

        if (remoteMessage.data.isNotEmpty()) {
            localNotification.link = remoteMessage.data["link"]
            localNotification.cls = MainActivity::class.java
        }

        sendNotification(localNotification)
    }

    private fun sendNotification(localNotification: LocalNotification) {
        generateLocalNotificationUseCase.invoke(localNotification).onEach {}.launchIn(
            CoroutineScope(job)
        )
    }



    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}