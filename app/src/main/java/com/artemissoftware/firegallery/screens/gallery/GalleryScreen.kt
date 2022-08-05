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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.firegallery.navigation.GalleryDestinationScreen
import com.artemissoftware.firegallery.screens.gallery.composables.GalleryCard

@Composable
fun GalleryScreen(navController: NavHostController) {

    val galleryViewModel: GalleryViewModel = hiltViewModel()
    val state = galleryViewModel.state.value

    BuildGalleryScreen(state = state, navController)

}

@Composable
private fun BuildGalleryScreen(
    state: GalleryState,
    navController: NavHostController
) {

    FGScaffold(isLoading = state.isLoading ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.galleries) { gallery->

                GalleryCard(
                    gallery = gallery,
                    onClick = { galleryId->
                        navController.navigate(GalleryDestinationScreen.Pictures.route)
                    }
                )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GalleryScreenPreview() {

    val state = GalleryState(galleries = Gallery.galleryMockList)
    BuildGalleryScreen(state, rememberNavController())
}
