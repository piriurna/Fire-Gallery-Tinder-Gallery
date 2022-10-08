package com.artemissoftware.data.mappers

import com.artemissoftware.data.firebase.remoteconfig.RemoteConfigContants
import com.artemissoftware.data.firebase.remoteconfig.models.SeasonFrc
import com.artemissoftware.domain.models.SeasonConfig
import com.google.common.reflect.TypeToken
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson

fun FirebaseRemoteConfig.toSeasonConfig() : SeasonFrc{

    return Gson().fromJson(this.getString(RemoteConfigContants.SEASON_CONFIG), object: TypeToken<SeasonFrc>(){}.type)

}

fun SeasonFrc.toSeasonConfig(): SeasonConfig{
    return SeasonConfig(this.backgroundColor, this.chipColor, this.seasonMessage)
}