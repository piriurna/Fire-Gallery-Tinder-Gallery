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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.button.FGCircularButton
import com.artemissoftware.common.composables.dialog.models.DialogOptions
import com.artemissoftware.common.composables.dialog.models.DialogType
import com.artemissoftware.common.composables.grid.FGStaggeredVerticalGrid
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.common.composables.topbar.AppBar
import com.artemissoftware.common.composables.topbar.FGCollapsedState
import com.artemissoftware.common.composables.topbar.FGTopBar
import com.artemissoftware.common.composables.topbar.TopBar
import com.artemissoftware.common.extensions.getNavigationResult
import com.artemissoftware.common.extensions.getParcelable
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.R
import com.artemissoftware.firegallery.navigation.GalleryDestinations
import com.artemissoftware.firegallery.navigation.NavigationArguments
import com.artemissoftware.firegallery.screens.gallery.models.GalleryUI
import com.artemissoftware.firegallery.screens.pictures.composables.PictureCard
import com.artemissoftware.firegallery.ui.UIEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PicturesScreen(
    navController: NavHostController,
    scaffoldState: FGScaffoldState
) {

    val viewModel: PicturesViewModel = hiltViewModel()
    val state = viewModel.state.value

    //TODO: Resolver isto
    val ll = stringResource(R.string.accept)

    LaunchedEffect(key1 = true) {

        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UIEvent.ShowInfoDialog -> {

                    val dialogType = DialogType.Info(
                        title = event.title,
                        description = event.message,
                        dialogOptions = DialogOptions(
                            confirmationText = ll,
                            confirmation = {
                                navController.popBackStack()
                            }
                        )
                    )

                    scaffoldState.showBottomBar(dialogType)
                }
            }
        }
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
        showTopBar = state.showOptions,
        title = state.galleryName,
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