package com.artemissoftware.firegallery.screens.gallery.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.firegallery.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GalleryCard(
    gallery: Gallery,
    onClick: (Int) -> Unit,
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        onClick = { onClick.invoke(gallery.id) }
    ) {
        Box {

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(gallery.imageUrl)
                    .size(Size.ORIGINAL)
                    .crossfade(2000)
                    .build()
            )

            GalleryImage(
                painter = painter,
                title = gallery.name,
                showText = (painter.state is AsyncImagePainter.State.Success)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GalleryCardPreview() {
    GalleryCard(gallery = Gallery.galleryMockList[0], onClick = {})
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
                Text(
                    text = title,
                    style = FGStyle.TextTextOswaldBold36,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GalleryImagePreview() {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://example.com/image.jpg")
            .size(Size.ORIGINAL)
            .placeholder(R.drawable.ic_launcher_background)
            .crossfade(1500)
            .build()
    )


    GalleryImage(painter, title = Gallery.galleryMockList[0].name, true)
}
