package com.artemissoftware.firegallery.screens.pictures.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.FGCard
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.screens.favorites.composables.FavoriteCard

@Composable
fun PictureCard(
    picture: Picture,
    onClick: (String) -> Unit,
) {

    FGCard(
        onClick =  { onClick(picture.id) }
    ) {

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(picture.imageUrl)
                .size(Size.ORIGINAL)
                .crossfade(500)
                .build()
        )

        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PictureCardPreview() {
    PictureCard(
        picture = Picture.picturesMockList[0],
        onClick = {}
    )
}
