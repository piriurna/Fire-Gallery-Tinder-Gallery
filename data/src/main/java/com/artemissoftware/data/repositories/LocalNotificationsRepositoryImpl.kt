package com.artemissoftware.data.repositories

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
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


    override suspend fun generateNotification(localNotification: LocalNotification) {


        with(notificationBuilder){

            localNotification.title?.let {
                setContentTitle(it)
            }
            localNotification.text?.let {
                setContentText(it)
            }
            localNotification.link?.let { link->
                setContentIntent(getClickPendingIntent(link =  link, localNotification = localNotification))
            }
        }

        notificationManager.notify(++notificationId, notificationBuilder.build())
    }


    private fun getClickPendingIntent(link: String, localNotification: LocalNotification): PendingIntent{
        val clickIntent = Intent(Intent.ACTION_VIEW, link.toUri(), context, localNotification.cls)

        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, getFlag())
        }

        return clickPendingIntent
    }

    private fun getFlag(): Int{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_IMMUTABLE
        else
            0
    }

    companion object {
        var notificationId = 0
    }
}