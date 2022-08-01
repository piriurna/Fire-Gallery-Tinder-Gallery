package com.artemissoftware.firegallery.di

import com.artemissoftware.data.firebase.cloudstore.source.CloudStoreSource
import com.artemissoftware.data.repositories.GalleryRepositoryImpl
import com.artemissoftware.domain.repositories.GalleryRepository
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
}