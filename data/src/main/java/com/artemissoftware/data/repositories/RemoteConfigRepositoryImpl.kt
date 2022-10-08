package com.artemissoftware.data.repositories

import com.artemissoftware.data.firebase.remoteconfig.RemoteConfigSource
import com.artemissoftware.domain.repositories.RemoteConfigRepository

class RemoteConfigRepositoryImpl(
    private val remoteConfigSource: RemoteConfigSource
) : RemoteConfigRepository {

    override suspend fun fetchValues(): Boolean = remoteConfigSource.fetchValues()

}