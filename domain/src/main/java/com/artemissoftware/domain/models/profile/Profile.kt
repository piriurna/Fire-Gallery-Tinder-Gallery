package com.artemissoftware.domain.models.profile

data class Profile(val appConfig: AppConfig, val user: User? = null){

    companion object{
        val mockProfile = Profile(appConfig = AppConfig.mockAppConfig)
    }

}
