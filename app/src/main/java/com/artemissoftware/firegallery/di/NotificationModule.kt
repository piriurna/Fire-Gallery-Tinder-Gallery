package com.artemissoftware.firegallery.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationCompat.VISIBILITY_SECRET
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.artemissoftware.firegallery.MainActivity
import com.artemissoftware.firegallery.R
import com.artemissoftware.firegallery.navigation.MY_ARG
import com.artemissoftware.firegallery.navigation.MY_LOLO
import com.artemissoftware.firegallery.navigation.NavigationArguments
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {

        with(context){

//            val intent = Intent(context/*, MyReceiver::class.java*/).apply {
//                //putExtra("MESSAGE", "Clicked!")
//            }
            val flag =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    PendingIntent.FLAG_IMMUTABLE
                else
                    0
//            val pendingIntent = PendingIntent.getBroadcast(
//                context,
//                0,
//                intent,
//                flag
//            )


            val clickIntent = Intent(
                Intent.ACTION_VIEW,
                "${NavigationArguments.ARTEMIS_SOFTWARE_URI}/${NavigationArguments.PICTURE_ID}=AABB".toUri(),
                //"$MY_URI/$MY_ARG=Coming from Notification&$MY_LOLO=lopes".toUri(),
                //"$MY_URI/$MY_ARG=Coming from Notification&".toUri(),
                context,
                MainActivity::class.java
            )
            val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(clickIntent)
                getPendingIntent(1, flag)
            }

            return NotificationCompat
                .Builder(this, getString(R.string.fg_fcm_notification_channel_id))
                .setContentTitle(getString(R.string.default_notification_title))
                .setContentText(getString(R.string.default_notification_text))
                .setSmallIcon(R.drawable.ic_fire_gallery_notification_logo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(clickPendingIntent)
        }
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)

        with(context) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    getString(R.string.fg_fcm_notification_channel_id),
                    getString(R.string.fg_fcm_notification_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
        }
        return notificationManager
    }

}