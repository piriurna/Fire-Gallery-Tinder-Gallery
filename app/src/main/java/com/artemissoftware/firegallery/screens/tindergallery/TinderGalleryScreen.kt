package com.artemissoftware.firegallery.screens.tindergallery

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.common.composables.button.FGPulsatingButton
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.extensions.swipeablecard.Direction
import com.artemissoftware.common.extensions.swipeablecard.SwipeableCardState
import com.artemissoftware.common.extensions.swipeablecard.rememberSwipeableCardState
import com.artemissoftware.common.models.SwipeResult
import com.artemissoftware.common.theme.FGStyle.TextAlbertSansBold28
import com.artemissoftware.common.theme.RedOrange
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.R
import com.artemissoftware.firegallery.screens.splash.composables.Logo
import com.artemissoftware.firegallery.screens.tindergallery.composables.TinderActionsRow
import com.artemissoftware.firegallery.screens.tindergallery.composables.TinderPictureCard
import kotlinx.coroutines.launch
import java.lang.Integer.max

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TinderGalleryScreen() {
    val viewModel : TinderGalleryViewModel = hiltViewModel()

    val state = viewModel.state.value

    BuildTinderGalleryScreen(state = state, events = viewModel::onTriggerEvent)
}


@Composable
fun BuildTinderGalleryScreen(
    state : TinderGalleyState,
    events : (TinderGalleryEvents) -> Unit = {},
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val screenWidth = with(LocalDensity.current) {
            LocalConfiguration.current.screenWidthDp.dp.toPx()
        }
        val screenHeight = with(LocalDensity.current) {
            LocalConfiguration.current.screenHeightDp.dp.toPx()
        }
        FGText(text = stringResource(R.string.swipe_to_add_photos_to_your_favorites), style = TextAlbertSansBold28)

        var currentOffset by remember {
            mutableStateOf(Offset(0f,0f))
        }

        var shouldReset by remember {
            mutableStateOf(false)
        }

        val resetOffset = animateOffsetAsState(
            targetValue = if(shouldReset) Offset(0f,0f) else currentOffset,
            animationSpec = tween(300, easing = LinearEasing),
            finishedListener = {
                if(shouldReset){
                    currentOffset = Offset(0f, 0f)
                    shouldReset = false
                }

            }
        )
        var states by remember {
            mutableStateOf<List<Pair<Picture, SwipeableCardState>>>(emptyList())
        }


        LaunchedEffect(key1 = state.pictures) {
            states = state.pictures.reversed()
                .map { it to SwipeableCardState(maxWidth = screenWidth, maxHeight = screenHeight) }
        }

        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            states.forEach { (picture, state) ->

                TinderPictureCard(
                    modifier = Modifier.clickable(enabled = false, onClick = {}),
                    picture = picture,
                    onSwiped = {
                        shouldReset = true
                        events(TinderGalleryEvents.GoToNextPicture(it))
                    },
                    onDrag = {
                        currentOffset = it
                    },
                    swipeState = state
                )
            }

            if(!state.showAddMoreButton()) {
                val scope = rememberCoroutineScope()

                TinderActionsRow(
                    currentXOffset = if(shouldReset) resetOffset.value.x else currentOffset.x,
                    maxWidth = constraints.maxWidth.toFloat(),
                    maxHeight = constraints.maxHeight.toFloat(),
                    onClick = {
                        scope.launch {
                            val last = states.reversed()
                                .firstOrNull {
                                    it.second.offset.value == Offset(0f, 0f)
                                }?.second
                            when(it) {
                                SwipeResult.REJECT -> {
                                    last?.swipe(Direction.Left)
                                }
                                SwipeResult.ACCEPT -> {
                                    last?.swipe(Direction.Right)
                                }
                            }
                            shouldReset = true
                            events(TinderGalleryEvents.GoToNextPicture(it))
                        }
                    },
                )
            } else {
                var shouldAnimate by remember {
                    mutableStateOf(false)
                }
                FGPulsatingButton(
                    modifier = Modifier.align(Alignment.Center),
                    buttonColor = MaterialTheme.colors.primary,
                    shouldAnimate = shouldAnimate,
                    pulseColor = RedOrange,
                    onClick = {
                        events(TinderGalleryEvents.FetchMorePictures)
                    }
                ) {
                    Logo() {
                        shouldAnimate = true
                    }
                }
            }
        }


    }
}


@Preview
@Composable
fun TinderGalleryScreenPreview() {
    var state by remember {
        mutableStateOf(TinderGalleyState(
            pictures = Picture.picturesMockList
        ))
    }
    BuildTinderGalleryScreen(
        state = state,
        events = {
            when(it) {
                is TinderGalleryEvents.GoToNextPicture -> {
                    state = state.copy(
                        currentIndex = max(state.currentIndex - 1, 0)
                    )
                }
                else -> {}
            }
        }
    )
}