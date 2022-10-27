package com.artemissoftware.data.mappers

import com.artemissoftware.data.firebase.remoteconfig.RemoteConfigContants
import com.artemissoftware.data.firebase.remoteconfig.models.ChipColorFrc
import com.artemissoftware.data.firebase.remoteconfig.models.SeasonFrc
import com.artemissoftware.data.firebase.remoteconfig.models.UserValidationFrc
import com.artemissoftware.domain.models.configurations.ChipColorConfig
import com.artemissoftware.domain.models.configurations.SeasonConfig
import com.artemissoftware.domain.models.configurations.UserValidationConfig
import com.google.common.reflect.TypeToken
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson

fun FirebaseRemoteConfig.toSeasonConfig() : SeasonFrc{
    return Gson().fromJson(this.getString(RemoteConfigContants.SEASON_CONFIG), object: TypeToken<SeasonFrc>(){}.type)
}

fun FirebaseRemoteConfig.toUserValidationConfig() : UserValidationFrc{
    return Gson().fromJson(this.getString(RemoteConfigContants.VALIDATION_CONFIG), object: TypeToken<UserValidationFrc>(){}.type)
}

fun ChipColorFrc.toChipColorConfig(): ChipColorConfig {
    return ChipColorConfig(this.start, this.end, this.icon)
}

fun SeasonFrc.toSeasonConfig(): SeasonConfig {
    return SeasonConfig(this.backgroundColor, this.chipColor.toChipColorConfig(), this.seasonMessage)
}

fun UserValidationFrc.toUserValidationConfig(): UserValidationConfig {
    return UserValidationConfig(
        emailRegex = this.emailRegex,
        passwordMaxLength = this.passwordMaxLength,
        passwordMinLength = this.passwordMinLength,
        usernameRegex = this.usernameRegex,
        usernameMaxLength = this.usernameMaxLength,
        usernameMinLength = this.usernameMinLength
    )
}