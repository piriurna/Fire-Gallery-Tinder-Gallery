package com.artemissoftware.domain.repositories

interface RemoteConfigRepository {

    suspend fun fetchValues(): Boolean

    fun getSeasonConfigs(): String
}