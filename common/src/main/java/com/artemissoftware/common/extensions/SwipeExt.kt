package com.artemissoftware.common.extensions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import com.artemissoftware.common.models.SwipeResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * Remebers the swipe state for a card on which action has been taken
 */
@Composable
@ExperimentalMaterialApi
fun rememberSwipeState(maxWidth: Float, maxHeight: Float): Swipe =
    remember { Swipe(maxWidth, maxHeight) }

/**
 * Setting the offset with animation
 *
 */
open class Swipe(val maxWidth: Float, val maxHeight: Float) {
    val offset = Animatable(Offset(0f,0f), Offset.VectorConverter)

    fun reset(scope: CoroutineScope) = scope.launch {
        launch { offset.animateTo(Offset(0f,0f), tween(600)) }
    }

    fun accepted(scope: CoroutineScope) = scope.launch {
        offset.animateTo(Offset(maxWidth * 2, offset.value.y), tween(600))
    }

    fun rejected(scope: CoroutineScope) = scope.launch {
        offset.animateTo(Offset(-(maxWidth * 2), offset.value.y), tween(600))
    }

    fun drag(scope: CoroutineScope, x: Float, y: Float) = scope.launch {
        launch { offset.animateTo(Offset(x,y)) }
    }
}

/**
 * Actual custom Modifier which handles the transition states from drog start to drag end
 * As mentioned in android dev blog here: https://developer.android.com/jetpack/compose/gestures#dragging
 * detectDragGestures has been used to continuously listen to touch pointer and perform action on drag events
 */
fun Modifier.swiper(
    state: Swipe,
    onDragReset: () -> Unit = {},
    onDragAccepted: () -> Unit,
    onDragRejected: () -> Unit,
): Modifier = composed {
    val scope = rememberCoroutineScope()
    Modifier.pointerInput(Unit) {
        detectDragGestures(
            onDragEnd = {
                when {
                    abs(state.offset.targetValue.x) < state.maxWidth / 3.5f -> {
                        state
                            .reset(scope)
                            .invokeOnCompletion { onDragReset() }
                    }
                    SwipeResult.isAcceptGesture(state.offset.targetValue.x) -> {
                        state
                            .accepted(scope)
                            .invokeOnCompletion { onDragAccepted() }
                    }
                    SwipeResult.isDismissGesture(state.offset.targetValue.x) -> {
                        state
                            .rejected(scope)
                            .invokeOnCompletion { onDragRejected() }
                    }
                }
            },
            onDrag = { change, dragAmount ->
                scope.launch {
                        val original = state.offset.targetValue
                        val summed = original + dragAmount
                        val newValue = Offset(
                            x = summed.x.coerceIn(-state.maxWidth, state.maxWidth),
                            y = summed.y.coerceIn(-state.maxHeight, state.maxHeight)
                        )
                        if (change.positionChange() != Offset.Zero) change.consume()
                        state.drag(scope, newValue.x, newValue.y)
                }
            }
        )
        /**
         * Doing translation on the graphics layer
         * which mimics the rotation and translation of tinder swipeable card. This can be improved
         * if I start swiping a card it first rotates along edges left or right according to drag and
         * slowly decrease alpha to look it more like dismissing
         */
    }.graphicsLayer {
        translationX = state.offset.value.x
        translationY = state.offset.value.y
        rotationZ = (state.offset.value.x / 60).coerceIn(-40f, 40f)
        alpha = ((state.maxWidth - abs(state.offset.value.x)) / state.maxWidth).coerceIn(0f, 1f)
    }
}