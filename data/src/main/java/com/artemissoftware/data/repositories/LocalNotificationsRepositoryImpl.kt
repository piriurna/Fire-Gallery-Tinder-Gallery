package com.artemissoftware.data.repositories

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.artemissoftware.data.firebase.cloudstore.source.CloudStoreSource
import com.artemissoftware.domain.models.LocalNotification
import com.artemissoftware.domain.repositories.GalleryRepository
import com.artemissoftware.domain.repositories.LocalNotificationsRepository
import javax.inject.Inject

class LocalNotificationsRepositoryImpl @Inject constructor(
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat,
    private val context: Context
) : LocalNotificationsRepository {


    override /*suspend*/ fun generateNotification(localNotification: LocalNotification) {
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0
//        val clickIntent = Intent(
//            Intent.ACTION_VIEW,
//            "${NavigationArguments.ARTEMIS_SOFTWARE_URI}/${NavigationArguments.PICTURE_ID}=AABB".toUri(),
//            //"$MY_URI/$MY_ARG=Coming from Notification&".toUri(),
//            this,
//            MainActivity::class.java
//        )
//        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack(clickIntent)
//            getPendingIntent(1, flag)
//        }

//            .setContentIntent(clickPendingIntent)
            //.setContentTitle(localNotification.title)
            //.build()

        with(notificationBuilder){

            localNotification.title?.let {
                setContentTitle(it)
            }
            localNotification.text?.let {
                setContentText(it)
            }
            localNotification.link?.let { link->

                val clickIntent = Intent(Intent.ACTION_VIEW, link.toUri(), context, localNotification.cls)

                val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
                    addNextIntentWithParentStack(clickIntent)
                    getPendingIntent(1, flag)
                }
                setContentIntent(clickPendingIntent)
            }
        }

        notificationManager.notify(1, notificationBuilder.build())
    }


}