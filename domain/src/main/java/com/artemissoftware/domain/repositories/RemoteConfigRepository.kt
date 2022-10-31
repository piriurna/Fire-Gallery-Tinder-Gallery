package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.FirebaseResponse
import com.artemissoftware.domain.models.configurations.SeasonConfig
import com.artemissoftware.domain.models.configurations.SeasonDetailConfig
import com.artemissoftware.domain.models.configurations.UserValidationConfig
import com.artemissoftware.domain.util.SeasonType

interface RemoteConfigRepository {

    suspend fun fetchValues(): FirebaseResponse<Boolean>

    fun getSeasonConfigs(): SeasonConfig

    fun getCurrentSeasonConfig() : SeasonDetailConfig

    fun getUserValidationConfigs(): UserValidationConfig
}