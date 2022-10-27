package com.artemissoftware.domain.models

data class UserFavoriteImages(val data: HashMap<String, List<String>>){

    companion object{

        val mockUserFavoriteImages : UserFavoriteImages = UserFavoriteImages(hashMapOf("x@x.com" to listOf("x"), "b@b.com" to listOf("xs"), "c@c.com" to listOf("xdd")))

    }

}
