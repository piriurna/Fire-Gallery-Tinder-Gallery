package com.artemissoftware.firegallery.screens.tindergallery.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.FGCard
import com.artemissoftware.common.composables.images.SwipeCard
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.models.SwipeResult
import com.artemissoftware.common.theme.FGStyle.TextAlbertSansBold28
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.domain.models.Picture

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TinderPictureCard(
    picture: Picture,
    onSwiped : (SwipeResult) -> Unit
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(picture.imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(500)
            .build()
    )

    SwipeCard(
        modifier = Modifier.fillMaxSize(),
        onSwiped = onSwiped
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize(),
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )

            FGText(
                text = picture.title,
                style = TextAlbertSansBold28,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 32.dp, start = 24.dp)
            )

        }
    }
}


@Preview(device = Devices.NEXUS_5X)
@Composable
fun TinderPictureCardPreview() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TinderPictureCard(
            Picture.picturesMockList[0],
            {}
        )
    }
}