package com.artemissoftware.domain.models

data class Gallery(
    val id: Int,
    val name: String,
    val imageUrl: String
){

    companion object{

        val galleryMockList = listOf(
            Gallery(id = 1 , name = "Terminator", imageUrl = "https://i2-prod.mirror.co.uk/incoming/article8771720.ece/ALTERNATES/s1200b/The-Terminator.jpg"),
            Gallery(id = 2 , name = "Xenomorph", imageUrl = "https://img.cdn-pictorem.com/uploads/collection/O/OT2LAH5PCS/900_Morteza-Golpoor_image00025_00016.jpg")
        )

    }

}
