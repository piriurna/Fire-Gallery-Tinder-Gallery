package com.artemissoftware.firegallery.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.artemissoftware.domain.models.LocalNotification
import com.artemissoftware.domain.usecases.UpdateFirebaseTokenUseCase
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

        Log.d("PushNotificationService", "onMessageReceived") //dkjw_B9cRamOSs7EZRwuwl:APA91bEQjtsoNc6NNbnj3-iJa_fD2Bgk-OWQMnB7v8UywW9c4Tfzr3OzaFO6Ro8BfAcFUxRd0rwFr1oW3GlZtWz7aT_4nOXnN2DOKIlE-bBqb8Jrv36f5EocalRpIHfBG8L7cVA5m15i

        val localNotification = LocalNotification(
            title = remoteMessage.notification?.title,
            text = remoteMessage.notification?.body
        )

        if (remoteMessage.data.isNotEmpty()) {
            Log.d("PushNotificationService", "Message data payload: ${remoteMessage.data}")

            localNotification.link = remoteMessage.data["link"]
            localNotification.cls = MainActivity::class.java
        }

        sendNotification(localNotification)
    }

    private fun sendNotification(localNotification: LocalNotification) {
        //val intent = Intent(this, MainActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
        //    PendingIntent.FLAG_IMMUTABLE)

//        val channelId = getString(R.string.default_notification_channel_id)
//        //val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val notificationBuilder = NotificationCompat.Builder(this, channelId)
//            .setSmallIcon(R.drawable.ic_fire_gallery_notification_logo)
//            .setContentTitle(title)
//            .setContentText(messageBody)
//            .setAutoCancel(true)
//            //.setSound(defaultSoundUri)
//            //.setContentIntent(pendingIntent)
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(channelId,
//                "Channel human readable title",
//                NotificationManager.IMPORTANCE_DEFAULT)
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }



    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}