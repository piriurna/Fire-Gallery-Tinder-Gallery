package com.artemissoftware.firegallery.screens.favorites.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.FGCard
import com.artemissoftware.common.composables.animations.models.PulsatingType
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.R
import com.artemissoftware.firegallery.screens.picturedetail.composables.FavoriteButton

@Composable
fun FavoriteCard(
    picture: Picture,
    onFavoriteClick: (String) -> Unit,
) {

    FGCard {

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(picture.imageUrl)
                .size(Size.ORIGINAL)
                .placeholder(R.drawable.ic_launcher_background)
                .crossfade(500)
                .build()
        )

        FavoriteContent(pictureId = picture.id, painter = painter, onClick = onFavoriteClick)
    }
}

@Composable
private fun FavoriteContent(
    pictureId: String,
    painter: AsyncImagePainter,
    onClick: (String) -> Unit,
) {
    val isFavorite = remember {
        mutableStateOf(false)
    }
    Box {

        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        FavoriteButton(
            pulsatingType = PulsatingType.LIMITED,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            onClickToFavorite = {
                onClick.invoke(pictureId)
            },
            onClickToRemoverFavorite = {
                onClick.invoke(pictureId)
            },
            isFavorite = true,
        )

    }
}



@Preview(showBackground = true)
@Composable
private fun FavoriteCardPreview() {
    FavoriteCard(
        picture = Picture.picturesMockList[0],
        onFavoriteClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoriteContentPreview() {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://example.com/image.jpg")
            .size(Size.ORIGINAL)
            .placeholder(R.drawable.ic_launcher_background)
            .crossfade(1500)
            .build()
    )


    FavoriteContent(pictureId = "1", painter = painter, {})
}