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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PicturesScreen(){

    val pictures = Picture.picturesMockList

    FGScaffold(isLoading = false) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            StaggeredVerticalGrid(
                numColumns = 2, //put the how many column you want
                modifier = Modifier.padding(4.dp)
            ) {
                pictures.forEach { picture ->

//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(4.dp),
//                        elevation = 12.dp,
//                        shape = RoundedCornerShape(12.dp)
//                    ) {
//                        AsyncImage(
//                            model = ImageRequest.Builder(LocalContext.current)
//                                .data(picture.imageUrl)
//                                .crossfade(true)
//                                .build(),
//                            contentDescription = stringResource(R.string.app_name),
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                    }


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        elevation = 12.dp,
                        shape = RoundedCornerShape(12.dp)

                    ) {
                        Box {

                            val painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(picture.imageUrl)
                                    .size(Size.ORIGINAL)
                                    .crossfade(2000)
                                    .build()
                            )

                            Image(
                                painter = painter,
                                contentDescription = "",
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop,
                            )



                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun GalleryScreenPreview() {

    PicturesScreen()
}