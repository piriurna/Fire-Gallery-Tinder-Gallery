package com.artemissoftware.firegallery.screens

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.theme.Orange
import com.artemissoftware.common.theme.RedOrange
import com.artemissoftware.firegallery.R
import com.artemissoftware.firegallery.screens.pictures.PicturesScreen
import com.artemissoftware.firegallery.screens.splash.composables.Logo

@Composable
fun SplashScreen() {

    val animateShape = remember { Animatable(100f) }
    LaunchedEffect(animateShape) {
        animateShape.animateTo(
            targetValue = 2f,
            animationSpec = repeatable(
                animation = tween(
                    durationMillis = 2000,
                    easing = LinearEasing,
                    delayMillis = 500
                ),
                repeatMode = RepeatMode.Restart,
                iterations = 3
            )
        )
    }


    val initialColor = Orange
    val targetColor = RedOrange
    val animateColor = remember { Animatable(initialColor) }

    LaunchedEffect(animateColor) {
        animateColor.animateTo(
            targetValue = targetColor,
            animationSpec = repeatable(
                animation = tween(
                    durationMillis = 2000,
                    easing = LinearEasing,
                    delayMillis = 500
                ),
                repeatMode = RepeatMode.Restart,
                iterations = 3
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()

    ) {

        Logo(
            modifier = Modifier.align(alignment = Alignment.Center),
            borderWidth = Dp(animateShape.value),
            borderColor = animateColor.asState().value
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {

    SplashScreen()
}