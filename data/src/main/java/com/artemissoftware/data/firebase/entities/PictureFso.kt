package com.artemissoftware.data.firebase.entities

data class PictureFso(
    val id: String = "",
    val author: String = "",
    val imageUrl: String = "",
    val tags: List<String> = emptyList(),
    val title: String = ""
)
