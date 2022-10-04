package com.artemissoftware.firegallery.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.artemissoftware.domain.usecases.UpdateFirebaseTokenUseCase
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

        val title = remoteMessage.notification?.title ?: "O meu titulo";
        val  text = remoteMessage.notification?.body?: "O meu text";

        Log.d("PushNotificationService", "Message Notification title: ${title}")
        Log.d("PushNotificationService", "Message Notification Body: ${text}")

     // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("PushNotificationService", "Message data payload: ${remoteMessage.data}")
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use WorkManager.
//                scheduleJob()
//            } else {
//                // Handle message within 10 seconds
//                handleNow()
//            }
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            //Log.d(TAG, "Message Notification Body: ${it.body}")
            //it.body?.let { body -> sendNotification(body) }
        }


        sendNotification(title, text)
        //NotificationManagerCompat.from(this).notify(1, createNotification(title = title, description = text).build());


//        // [START_EXCLUDE]
//        // There are two types of messages data messages and notification messages. Data messages are handled
//        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
//        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
//        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
//        // When the user taps on the notification they are returned to the app. Messages containing both notification
//        // and data payloads are treated as notification messages. The Firebase console always sends notification
//        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
//        // [END_EXCLUDE]
//



//        // TODO(developer): Handle FCM messages here.
//        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: ${remoteMessage.from}")
//
//        // Check if message contains a data payload.
//        if (remoteMessage.data.isNotEmpty()) {
//            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
//
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use WorkManager.
//                scheduleJob()
//            } else {
//                // Handle message within 10 seconds
//                handleNow()
//            }
//        }
//
//        // Check if message contains a notification payload.
//        remoteMessage.notification?.let {
//            Log.d(TAG, "Message Notification Body: ${it.body}")
//            it.body?.let { body -> sendNotification(body) }
//        }
//
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.
    }
//
////    override fun onMessageReceived(message: RemoteMessage) {
//    //Log.d(TAG, "Message data payload: " + remoteMessage.getData());
////        String title = remoteMessage.getNotification().getTitle();
////        String text = remoteMessage.getNotification().getBody();
////
////        final String CHANNEL_ID = "HEADS_UP_NOTIFICATION";
////
////        NotificationChannel channel = new NotificationChannel(
////            CHANNEL_ID,
////            "Heads Up Notification",
////            NotificationManager.IMPORTANCE_HIGH
////        );
////
////        Notification.Builder notification =
////        new Notification.Builder(this, CHANNEL_ID)
////        .setContentTitle(title)
////            .setContentText(text)
////            .setSmallIcon(R.drawable.ic_launcher_background)
////            .setAutoCancel(true);
////
////        NotificationManagerCompat.from(this).notify(1, notification.build());
////
////        super.onMessageReceived(remoteMessage);
////        super.onMessageReceived(message)
////    }
//
//
//    /**
//     * Create and show a simple notification containing the received FCM message.
//     *
//     * @param messageBody FCM message body received.
//     */
    private fun sendNotification(title : String, messageBody: String) {
        //val intent = Intent(this, MainActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
        //    PendingIntent.FLAG_IMMUTABLE)

        val channelId = getString(R.string.default_notification_channel_id)
        //val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_fire_gallery_fcm_logo)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            //.setSound(defaultSoundUri)
            //.setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }



    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}