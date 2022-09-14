package com.artemissoftware.firegallery.screens.pictures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.grid.StaggeredVerticalGrid
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.navigation.GalleryDestinations
import com.artemissoftware.firegallery.navigation.NavigationArguments
import com.artemissoftware.firegallery.screens.gallery.GalleryState
import com.artemissoftware.firegallery.screens.pictures.composables.PictureCard

@Composable
fun PicturesScreen(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
){

    val galleryId = backStackEntry.arguments!!.getString(NavigationArguments.GALLERY_ID)!!

    val viewModel: PicturesViewModel = hiltViewModel()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true){
        viewModel.onTriggerEvent(PictureEvents.GetPictures(galleryId = galleryId.toInt()))
    }

    BuildPicturesScreen(state = state, navController = navController)

}

@Composable
private fun BuildPicturesScreen(
    state: PictureState,
    navController: NavHostController
) {

    FGScaffold(
        isLoading = state.isLoading,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            StaggeredVerticalGrid(
                numColumns = 2, //put the how many column you want
                modifier = Modifier.padding(4.dp)
            ) {
                state.pictures.forEach { picture ->

                    PictureCard(
                        picture = picture,
                        onClick = { pictureId ->
                            navController.navigate(GalleryDestinations.PictureDetail.withArgs(pictureId))
                        }
                    )

                }
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
private fun GalleryScreenPreview() {

    BuildPicturesScreen(state = PictureState(pictures = Picture.picturesMockList), navController = rememberNavController())
}