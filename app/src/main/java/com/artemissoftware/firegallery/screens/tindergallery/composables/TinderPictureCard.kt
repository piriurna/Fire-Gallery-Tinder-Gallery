package com.artemissoftware.firegallery.screens.tindergallery.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.artemissoftware.common.composables.FGCard
import com.artemissoftware.common.composables.button.FGCircularButton
import com.artemissoftware.common.composables.images.SwipeCard
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.extensions.rememberSwipeState
import com.artemissoftware.common.models.SwipeResult
import com.artemissoftware.common.theme.FGStyle.TextAlbertSansBold28
import com.artemissoftware.domain.models.Gallery
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.R
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TinderPictureCard(
    picture: Picture,
    onSwiped : (SwipeResult) -> Unit
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(picture.imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(500)
            .build()
    )

    var swiped by remember { mutableStateOf(false) }

    val onSwipedLocal = { swipeResult : SwipeResult->
        swiped = true
        onSwiped(swipeResult)
    }

    val actionButtonSize = 48.dp

    if(swiped.not()) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            val swipeState = rememberSwipeState(maxWidth = constraints.maxWidth.toFloat(), maxHeight = constraints.maxHeight.toFloat())
                SwipeCard(
                    modifier = Modifier
                        .fillMaxSize(),
                    onSwiped = onSwipedLocal,
                    swipeState = swipeState
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Image(
                            modifier = Modifier
                                .background(Color.Black)
                                .fillMaxSize(),
                            painter = painter,
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                        )

                        FGText(
                            text = picture.title,
                            style = TextAlbertSansBold28,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 32.dp, start = 24.dp)
                        )

                    }
                }

                FGCircularButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .graphicsLayer {
                            val isLikeSwipe = swipeState.offsetX.value < 0f
                            val targetXPosition = constraints.maxWidth.toFloat() / 2f
                            val currentXPosition = -min(targetXPosition - 24.dp.toPx(), swipeState.offsetX.value.absoluteValue)
                            val targetYPosition = constraints.maxHeight.toFloat() / 2f
                            val currentYPosition = -min(targetYPosition - 24.dp.toPx(), swipeState.offsetX.value.absoluteValue * constraints.maxHeight.toFloat()/constraints.maxWidth.toFloat())
                            if(isLikeSwipe) {
                                translationX = currentXPosition
                                translationY = currentYPosition

                                val scale = min(2.5f, 1 + swipeState.offsetX.value.absoluteValue / constraints.maxWidth.toFloat())

                                scaleX = scale
                                scaleY = scale
                            } else {
                                translationY = -currentXPosition * (constraints.maxHeight.toFloat() / constraints.maxWidth)
                            }
                        },
                    imageVector = Icons.Default.Favorite,
                    buttonSize = actionButtonSize,
                    color = Color.Green
                )

                FGCircularButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .graphicsLayer {
                            val isDismissSwipe = swipeState.offsetX.value > 0f
                            val targetXPosition = constraints.maxWidth.toFloat() / 2f
                            val currentXPosition = min(targetXPosition - 24.dp.toPx(), swipeState.offsetX.value.absoluteValue)
                            val targetYPosition = constraints.maxHeight.toFloat() / 2f
                            val currentYPosition = -min(targetYPosition - 24.dp.toPx(), swipeState.offsetX.value.absoluteValue * constraints.maxHeight.toFloat()/constraints.maxWidth.toFloat())
                            if(isDismissSwipe) {
                                translationX = currentXPosition
                                translationY = currentYPosition

                                val scale = min(2.5f, 1 + swipeState.offsetX.value.absoluteValue / constraints.maxWidth.toFloat())

                                scaleX = scale
                                scaleY = scale
                            } else {
                                translationY = currentXPosition * (constraints.maxHeight.toFloat() / constraints.maxWidth)
                            }
                    },
                    imageVector = Icons.Default.Cancel,
                    color = Color.Red
                )
        }
    }

}


@Preview(device = Devices.NEXUS_5X)
@Composable
fun TinderPictureCardPreview() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TinderPictureCard(
            Picture.picturesMockList[0],
            {}
        )
    }
}