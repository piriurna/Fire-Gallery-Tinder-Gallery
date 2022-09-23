package com.artemissoftware.firegallery.screens.pictures

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.button.FGCircularButton
import com.artemissoftware.common.composables.grid.FGStaggeredVerticalGrid
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.topbar.AppBar
import com.artemissoftware.common.composables.topbar.FGCollapsedState
import com.artemissoftware.common.composables.topbar.FGTopBar
import com.artemissoftware.common.composables.topbar.TopBar
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.navigation.GalleryDestinations
import com.artemissoftware.firegallery.screens.pictures.composables.PictureCard

@Composable
fun PicturesScreen(navController: NavHostController){

    val viewModel: PicturesViewModel = hiltViewModel()
    val state = viewModel.state.value

    BuildPicturesScreen(state = state, navController = navController)

}

@Composable
private fun BuildPicturesScreen(
    state: PictureState,
    navController: NavHostController
) {

    FGScaffold(
//        isLoading = state.isLoading,
        showTopBar = !state.isLoading,
        title = "null",
        onNavigationClick = {
            navController.popBackStack()
        },
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            FGStaggeredVerticalGrid(
                numColumns = 2,
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