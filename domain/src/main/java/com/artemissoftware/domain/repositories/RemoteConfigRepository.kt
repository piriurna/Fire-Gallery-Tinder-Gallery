package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.configurations.SeasonConfig
import com.artemissoftware.domain.models.configurations.UserValidationConfig

interface RemoteConfigRepository {

    suspend fun fetchValues(): Boolean

    fun getSeasonConfigs(): SeasonConfig

    fun getUserValidationConfigs(): UserValidationConfig
}