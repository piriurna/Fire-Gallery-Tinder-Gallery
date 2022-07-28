package com.artemissoftware.firegallery.screens.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.firegallery.screens.gallery.composables.GalleryCard

@Composable
fun GalleryScreen() {

    val galleries = Gallery.galleryMockList

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(galleries) { gallery->

            GalleryCard(gallery = gallery)

        }

    }

}

@Preview(showBackground = true)
@Composable
private fun GalleryScreenPreview() {
    GalleryScreen()
}
