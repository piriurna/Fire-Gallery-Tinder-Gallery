package com.artemissoftware.data.repositories

import com.artemissoftware.data.firebase.remoteconfig.RemoteConfigSource
import com.artemissoftware.data.mappers.toSeasonConfig
import com.artemissoftware.data.mappers.toUserValidationConfig
import com.artemissoftware.domain.repositories.RemoteConfigRepository

class RemoteConfigRepositoryImpl(
    private val remoteConfigSource: RemoteConfigSource
) : RemoteConfigRepository {

    override suspend fun fetchValues(): Boolean = remoteConfigSource.fetchValues()

    override fun getSeasonConfigs() = remoteConfigSource.seasonConfig.toSeasonConfig()

    override fun getUserValidationConfigs() = remoteConfigSource.userValidationConfig.toUserValidationConfig()


}