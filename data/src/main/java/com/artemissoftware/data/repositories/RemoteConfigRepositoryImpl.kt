package com.artemissoftware.data.repositories

import com.artemissoftware.data.firebase.source.RemoteConfigSource
import com.artemissoftware.data.mappers.toSeasonConfig
import com.artemissoftware.data.mappers.toSeasonDetailConfig
import com.artemissoftware.data.mappers.toUserValidationConfig
import com.artemissoftware.data.util.SeasonUtil
import com.artemissoftware.domain.models.configurations.SeasonDetailConfig
import com.artemissoftware.domain.repositories.RemoteConfigRepository
import com.artemissoftware.domain.util.SeasonType
import java.util.*

class RemoteConfigRepositoryImpl(
    private val remoteConfigSource: RemoteConfigSource
) : RemoteConfigRepository {

    override suspend fun fetchValues(): Boolean = remoteConfigSource.fetchValues()

    override fun getSeasonConfigs() = remoteConfigSource.seasonConfig.toSeasonConfig()

    override fun getCurrentSeasonConfig(): SeasonDetailConfig {

        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1

        val seasonType = SeasonUtil.findSeason(month = month)
        return remoteConfigSource.seasonConfig.toSeasonDetailConfig(seasonType)
    }

    override fun getUserValidationConfigs() = remoteConfigSource.userValidationConfig.toUserValidationConfig()


}