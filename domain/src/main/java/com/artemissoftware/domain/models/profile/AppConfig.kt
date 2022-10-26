package com.artemissoftware.domain.models.profile

data class AppConfig(
    val notifications: Boolean = false,
    val firebaseToken: String? = null,
    var favorites: List<String> = emptyList(),
    var user: User? = null
){

    companion object{

        val mockAppConfig : AppConfig = AppConfig(notifications = false, firebaseToken = "lkdfjhvgklzjdhnjf")

    }

}
