package com.artemissoftware.firegallery.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.dialog.models.DialogOptions
import com.artemissoftware.common.composables.dialog.models.DialogType
import com.artemissoftware.common.composables.grid.FGStaggeredVerticalGrid
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.R
import com.artemissoftware.firegallery.screens.favorites.composables.FavoriteCard
import com.artemissoftware.firegallery.screens.picturedetail.PictureDetailEvents
import com.artemissoftware.firegallery.screens.pictures.PictureState
import com.artemissoftware.firegallery.ui.UIEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    scaffoldState: FGScaffoldState
){

    val viewModel: FavoritesViewModel = hiltViewModel()
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
                                //TODO: ir para a galeria tab
                                //TODO: só aparece uma vez, por ser eventflow.resolver
                                //navController.popBackStack()
                            }
                        )
                    )

                    scaffoldState.showBottomBar(dialogType)
                }
            }
        }
    }


    BuildFavoritesScreen(state = state, navController = navController, events = viewModel::onTriggerEvent)

}

@Composable
private fun BuildFavoritesScreen(
    state: PictureState,
    events: ((FavoriteEvents) -> Unit)? = null,
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
                        onFavoriteClick = { pictureId ->
                            events?.invoke(FavoriteEvents.Remove(pictureId))
                        }
                    )

                }
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
private fun BuildFavoritesScreenPreview() {

    BuildFavoritesScreen(state = PictureState(pictures = Picture.picturesMockList), navController = rememberNavController())
}