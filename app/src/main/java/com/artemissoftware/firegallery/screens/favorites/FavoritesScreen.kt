package com.artemissoftware.firegallery.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.grid.FGStaggeredVerticalGrid
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.screens.favorites.composables.FavoriteCard
import com.artemissoftware.firegallery.screens.pictures.PictureState

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    //backStackEntry: NavBackStackEntry
){

    //val galleryId = backStackEntry.arguments!!.getString(NavigationArguments.GALLERY_ID)!!

    val viewModel: FavoritesViewModel = hiltViewModel()
    val state = viewModel.state.value

//    LaunchedEffect(key1 = true){
//        viewModel.onTriggerEvent(PictureEvents.GetPictures(galleryId = 1))
//    }

    BuildFavoritesScreen(state = state, navController = navController)

}

@Composable
private fun BuildFavoritesScreen(
    state: PictureState,
    navController: NavHostController
) {

    FGScaffold(isLoading = state.isLoading) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            FGStaggeredVerticalGrid(
                numColumns = 2, //put the how many column you want
                modifier = Modifier.padding(4.dp)
            ) {
                state.pictures.forEach { picture ->

                    FavoriteCard(
                        picture = picture,
                        onClick = {
                            //navController.navigate(GalleryDestinations.PictureDetail.route)
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

    BuildFavoritesScreen(state = PictureState(pictures = Picture.picturesMockList), navController = rememberNavController())
}