package com.artemissoftware.firegallery.screens.gallery.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.FGCard
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.firegallery.R

@Composable
fun GalleryCard(
    gallery: Gallery,
    onClick: (Int) -> Unit,
) {

    FGCard(
        onClick = { onClick.invoke(gallery.id) }
    ) {

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(gallery.imageUrl)
                .size(Size.ORIGINAL)
                .crossfade(1500)
                .build()
        )

        GalleryImage(
            painter = painter,
            title = gallery.name,
            showText = (painter.state is AsyncImagePainter.State.Success)
        )

    }
}



@Composable
private fun GalleryImage(
    painter: AsyncImagePainter,
    title: String,
    showText: Boolean
) {

    Box {

        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        if (showText) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                FGText(
                    text = title,
                    color = Color.White,
                    style = FGStyle.TextOswaldBold36,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GalleryCardPreview() {
    GalleryCard(gallery = Gallery.galleryMockList[0], onClick = {})
}

@Preview(showBackground = true)
@Composable
private fun GalleryImagePreview() {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://upload.wikimedia.org/wikipedia/commons/7/79/2010-brown-bear.jpg")
            .size(Size.ORIGINAL)
            .placeholder(R.drawable.ic_launcher_background)
            .crossfade(1500)
            .build()
    )


    GalleryImage(painter, title = Gallery.galleryMockList[0].name, true)
}
