package com.artemissoftware.firegallery.screens.pictures

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.grid.StaggeredVerticalGrid
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.R
import com.artemissoftware.firegallery.navigation.GalleryDestinationScreen
import com.artemissoftware.firegallery.screens.gallery.GalleryViewModel
import com.artemissoftware.firegallery.screens.pictures.composables.PictureCard
import java.util.*

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
                            navController.navigate(GalleryDestinationScreen.PictureDetail.route)
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