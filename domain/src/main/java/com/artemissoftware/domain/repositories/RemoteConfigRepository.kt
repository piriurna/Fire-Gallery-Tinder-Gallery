package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.configurations.SeasonConfig

interface RemoteConfigRepository {

    suspend fun fetchValues(): Boolean

    fun getSeasonConfigs(): SeasonConfig
}