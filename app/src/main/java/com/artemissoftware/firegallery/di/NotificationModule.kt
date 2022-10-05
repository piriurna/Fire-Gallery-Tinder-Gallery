package com.artemissoftware.firegallery.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationCompat.VISIBILITY_SECRET
import androidx.core.app.NotificationManagerCompat
import com.artemissoftware.firegallery.R
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

            return NotificationCompat
                .Builder(this, getString(R.string.fg_fcm_notification_channel_id))
                .setContentTitle(getString(R.string.default_notification_title))
                .setContentText(getString(R.string.default_notification_text))
                .setSmallIcon(R.drawable.ic_fire_gallery_notification_logo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
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