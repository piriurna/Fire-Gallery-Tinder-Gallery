package com.artemissoftware.domain.models.profile

data class Profile(
    val notifications: Boolean = false,
    val firebaseToken: String? = null,
    val favorites: List<String> = emptyList()
){

    companion object{

        val mockProfile : Profile = Profile(notifications = false, firebaseToken = "lkdfjhvgklzjdhnjf")

    }

}
