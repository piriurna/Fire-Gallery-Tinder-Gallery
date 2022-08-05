package com.artemissoftware.domain.models

data class Picture(
    val id: String,
    val imageUrl: String
){

    companion object{

        val picturesMockList = listOf(
            Picture(id = "1", imageUrl = "https://encenasaudemental.com/wp-content/uploads/2014/09/artemis.jpg" ),
            Picture(id = "2", imageUrl = "https://unebrasil.org/wp-content/uploads/2020/02/Artemis.jpg" ),
            Picture(id = "3", imageUrl = "https://4.bp.blogspot.com/_bRN4lXgrpG4/Sw0vgFY8qyI/AAAAAAAAA1A/Xs7chsD73-o/s1600/artemis2.jpg" ),
            Picture(id = "4", imageUrl = "https://ih1.redbubble.net/image.2336478000.4145/flat,128x128,075,t-pad,128x128,f8f8f8.jpg" ),
            Picture(id = "1", imageUrl = "https://encenasaudemental.com/wp-content/uploads/2014/09/artemis.jpg" ),
            Picture(id = "2", imageUrl = "https://unebrasil.org/wp-content/uploads/2020/02/Artemis.jpg" ),
            Picture(id = "3", imageUrl = "https://4.bp.blogspot.com/_bRN4lXgrpG4/Sw0vgFY8qyI/AAAAAAAAA1A/Xs7chsD73-o/s1600/artemis2.jpg" ),
            Picture(id = "4", imageUrl = "https://ih1.redbubble.net/image.2336478000.4145/flat,128x128,075,t-pad,128x128,f8f8f8.jpg" ),
        )

    }


}
