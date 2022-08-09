package com.artemissoftware.common.composables.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Pulsating(
    pulseFraction: Float = 1.2f,
    content: @Composable () -> Unit
) {

    val animateShape = remember { Animatable(1f) }

    LaunchedEffect(animateShape) {
        animateShape.animateTo(
            targetValue = 1.2f,
            animationSpec = repeatable(
                animation = tween(
                    durationMillis = 1000,
                    easing = LinearOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse,
                iterations = 5
            )
        )
    }


    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.scale(animateShape.value)) {
        content()
    }
}

@Preview
@Composable
fun PulsatingPreview() {
    Pulsating(
        content = {
            Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "")
        }
    )
}