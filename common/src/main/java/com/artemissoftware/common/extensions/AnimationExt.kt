package com.artemissoftware.common.extensions

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp

@Composable
fun scaleInfiniteTransition(
    initialValue: Float = 0f,
    targetValue: Float = 1f,
    durationMillis: Int,
): Float {
    val infiniteTransition = rememberInfiniteTransition()
    val scale: Float by infiniteTransition.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    return scale
}

@Composable
fun DrawCircleOnCanvas(
    circleSize : Dp,
    scale: Float,
    color: Color,
) {
    Canvas(
        modifier = Modifier
            .size(circleSize)
    ) {
        drawCircle(
            color = color,
            center = Offset(
                x = size.width / 2,
                y = size.height / 2
            ),
            radius = ((circleSize * scale) / 2f).toPx()
        )
    }
}