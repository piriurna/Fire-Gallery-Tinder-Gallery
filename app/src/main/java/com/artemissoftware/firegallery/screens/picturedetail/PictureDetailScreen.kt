package com.artemissoftware.firegallery.screens.picturedetail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.animations.models.PulsatingType
import com.artemissoftware.common.composables.scaffold.FGBottomSheetScaffold
import com.artemissoftware.common.models.Chip
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.screens.picturedetail.composables.FavoriteButton
import com.artemissoftware.firegallery.screens.picturedetail.composables.PictureInformation

@Composable
fun PictureDetailScreen(
    viewModel: PictureDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val isFavorite = viewModel.isFavorite

    BuildPictureDetailScreen(state = state, events = viewModel::onTriggerEvent, isFavorite = isFavorite)
}


@Composable
private fun BuildPictureDetailScreen(
    state: PictureDetailState,
    events: ((PictureDetailEvents) -> Unit)? = null,
    isFavorite: State<Boolean>,
) {


    FGBottomSheetScaffold(
        isLoading = state.isLoading,
        showTopBar = state.picture != null,
        title = "null",
        subtitle = "null",
        onNavigationClick = {

        },
        topBarOptionComposable = {

            state.picture?.let { picture->

                FavoriteButton(
                    isFavorite = isFavorite.value,
                    pulsatingType = PulsatingType.LIMITED,
                    onClickToFavorite = {
                        events?.invoke(PictureDetailEvents.FavoritePicture(picture.id, true))
                    },
                    onClickToRemoverFavorite = {
                        events?.invoke(PictureDetailEvents.FavoritePicture(picture.id, false))
                    },
                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 0.dp, topEnd = 46.dp),
        sheetContent = {

            PictureInformation(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 16.dp),
                picture = state.picture,
                tags = Chip.mockChips //TODO: mudar para tags que vem do firebase
            )

        },
        content = {
            Content(state.picture)
        }
    )
}

@Composable
private fun Content(picture: Picture?) {

    picture?.let { it->
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(it.imageUrl)
                .size(Size.ORIGINAL)
                .build()
        )

        Box{

            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }

}





@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun BuildPictureDetailScreenPreview() {

    val state = PictureDetailState(picture = Picture.picturesMockList[0], isLoading = false)
    BuildPictureDetailScreen(state = state, isFavorite = mutableStateOf(true))
}