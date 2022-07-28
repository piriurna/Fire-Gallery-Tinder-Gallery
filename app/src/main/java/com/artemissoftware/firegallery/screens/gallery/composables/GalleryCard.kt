package com.artemissoftware.firegallery.screens.gallery.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.firegallery.R

@Composable
fun GalleryCard(gallery: Gallery) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
    ) {
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = gallery.name,
                    style = FGStyle.TextTextOswaldBold36,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipSurfacePreview() {
    GalleryCard(gallery = Gallery.galleryMockList[0])
}
