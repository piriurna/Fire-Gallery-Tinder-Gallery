package com.artemissoftware.firegallery.di

import android.app.Application
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.artemissoftware.data.firebase.cloudstore.source.CloudStoreSource
import com.artemissoftware.data.firebase.remoteconfig.RemoteConfigSource
import com.artemissoftware.data.repositories.DataStoreRepositoryImpl
import com.artemissoftware.data.repositories.GalleryRepositoryImpl
import com.artemissoftware.data.repositories.LocalNotificationsRepositoryImpl
import com.artemissoftware.data.repositories.RemoteConfigRepositoryImpl
import com.artemissoftware.domain.repositories.DataStoreRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import com.artemissoftware.domain.repositories.LocalNotificationsRepository
import com.artemissoftware.domain.repositories.RemoteConfigRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGalleryRepository(cloudStoreSource: CloudStoreSource): GalleryRepository {
        return GalleryRepositoryImpl(cloudStoreSource)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(app: Application): DataStoreRepository {
        return DataStoreRepositoryImpl(app)
    }

    @Provides
    @Singleton
    fun provideLocalNotificationsRepository(app: Application, notification : NotificationCompat.Builder, notificationManager: NotificationManagerCompat): LocalNotificationsRepository {
        return LocalNotificationsRepositoryImpl(notification, notificationManager, app)
    }

    @Provides
    @Singleton
    fun provideRemoteConfigRepository(remoteConfigSource: RemoteConfigSource): RemoteConfigRepository {
        return RemoteConfigRepositoryImpl(remoteConfigSource)
    }
}