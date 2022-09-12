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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.grid.StaggeredVerticalGrid
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.firegallery.navigation.GalleryDestinations
import com.artemissoftware.firegallery.screens.pictures.composables.PictureCard

@Composable
fun PicturesScreen(navController: NavHostController){

    val viewModel: PicturesViewModel = hiltViewModel()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true){
        viewModel.onTriggerEvent(PictureEvents.GetPictures(1))
    }



    FGScaffold(isLoading = state.isLoading) {
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
                        onClick = {
                            navController.navigate(GalleryDestinations.PictureDetail.route)
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

    PicturesScreen(rememberNavController())
}