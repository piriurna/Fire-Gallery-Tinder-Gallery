package com.artemissoftware.firegallery.screens.picturedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.animations.models.PulsatingType
import com.artemissoftware.common.composables.button.FGCircularButton
import com.artemissoftware.common.composables.chip.ChipSurface
import com.artemissoftware.common.composables.scaffold.FGBottomSheetScaffold
import com.artemissoftware.common.models.Chip
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.navigation.NavigationArguments
import com.artemissoftware.firegallery.screens.picturedetail.composables.FavoriteButton
import com.artemissoftware.firegallery.screens.picturedetail.composables.PictureInformation

@Composable
fun PictureDetailScreen(
    backStackEntry: NavBackStackEntry
) {
    val pictureId = backStackEntry.arguments!!.getString(NavigationArguments.PICTURE_ID)!!

    val viewModel: PictureDetailViewModel = hiltViewModel()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true){
        viewModel.onTriggerEvent(PictureDetailEvents.GetPicture(pictureId))
    }

    BuildPictureDetailScreen(state = state)

}


@Composable
private fun BuildPictureDetailScreen(state: PictureDetailState) {

    FGBottomSheetScaffold(
        isLoading = state.isLoading,
        showTopBar = state.picture != null,
        title = "null",
        subtitle = "null",
        onNavigationClick = {

        },
        topBarOptionComposable = {

            FavoriteButton(
                pulsatingType = PulsatingType.LIMITED,
                onClickToFavorite = {},
                onClickToRemoverFavorite = {},
            )
        },
        sheetShape = RoundedCornerShape(topStart = 0.dp, topEnd = 46.dp),
        sheetContent = {

            PictureInformation(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 16.dp),
                title = "title",
                author = "authoes",
                filters = Chip.mockChips
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





@Preview(showBackground = true)
@Composable
private fun BuildPictureDetailScreenPreview() {

    val state = PictureDetailState(picture = Picture.picturesMockList[0], isLoading = false)
    BuildPictureDetailScreen(state = state)
}