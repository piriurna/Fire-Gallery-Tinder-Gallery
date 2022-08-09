package com.artemissoftware.firegallery.screens.picturedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.scaffold.FGBottomSheetScaffold
import com.artemissoftware.common.models.Chip
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.screens.gallery.GalleryViewModel
import com.artemissoftware.firegallery.screens.picturedetail.composables.PictureInformation
import com.artemissoftware.firegallery.screens.pictures.PictureEvents

@Composable
fun PictureDetailScreen() {

    val viewModel: PictureDetailViewModel = hiltViewModel()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true){
        viewModel.onTriggerEvent(PictureDetailEvents.GetPicture("AABB"))
    }

    FGBottomSheetScaffold(
        isLoading = state.isLoading,
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

        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }



}


@Preview(showBackground = true)
@Composable
private fun PictureDetailScreenPreview() {

    PictureDetailScreen()
}