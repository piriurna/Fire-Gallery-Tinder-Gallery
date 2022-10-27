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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.domain.models.Picture

@Composable
fun PictureCard(
    picture: Picture,
    onClick: (String) -> Unit,
) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick(picture.id) },
        elevation = 12.dp,
        shape = RoundedCornerShape(12.dp)

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