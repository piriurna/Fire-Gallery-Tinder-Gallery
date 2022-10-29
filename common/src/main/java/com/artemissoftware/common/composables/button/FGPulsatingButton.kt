package com.artemissoftware.common.composables.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.artemissoftware.common.extensions.DrawCircleOnCanvas
import com.artemissoftware.common.extensions.scaleInfiniteTransition
import kotlinx.coroutines.delay

@Composable
fun FGPulsatingButton(
    modifier : Modifier = Modifier,
    buttonColor : Color,
    innerPulseColor : Color = buttonColor.copy(alpha = 0.5f),
    centerPulseColor : Color = buttonColor.copy(alpha = 0.25f),
    buttonIcon : ImageVector = Icons.Default.Add,
    size : Dp? = null
) {

    var canvasSize by remember {
        mutableStateOf(0)
    }

    val minimumScale = 0.2f

    val buttonTargetScale = 0.5f

    var centerPulseTargetScale by remember {
        mutableStateOf(0f) // It starts at 0 because we have to add a delay in the LaunchedEffect
    }

    var outerPulseTargetScale by remember {
        mutableStateOf(0f) // It starts at 0 because we have to add a delay in the LaunchedEffect
    }

    LaunchedEffect(Unit) {
        delay(100L)

        centerPulseTargetScale = 0.75f

        delay(100L)

        outerPulseTargetScale = 1f
    }

    val parentSizeModifier = if(size == null) modifier else modifier.size(size)

    Box(
        modifier = parentSizeModifier.onSizeChanged {
            canvasSize = if(it.width == 0) 250 else it.width
        },
        contentAlignment = Alignment.Center
    ) {
        

        DrawCircleOnCanvas(
            circleSize = with(LocalDensity.current) {
                canvasSize.toDp()
            },
            scale = scaleInfiniteTransition(initialValue = minimumScale, targetValue = outerPulseTargetScale, durationMillis = 1000),
            color = centerPulseColor,
        )

        DrawCircleOnCanvas(
            circleSize = with(LocalDensity.current) {
                canvasSize.toDp()
            },
            scale = scaleInfiniteTransition(initialValue = minimumScale, targetValue = centerPulseTargetScale, durationMillis = 1000),
            color = innerPulseColor
        )

        DrawCircleOnCanvas(
            circleSize = with(LocalDensity.current) {
                canvasSize.toDp()
            },
            scale = scaleInfiniteTransition(initialValue = minimumScale, targetValue = buttonTargetScale, durationMillis = 1000),
            color = buttonColor,
        )


        Image(
            modifier = Modifier.size(with(LocalDensity.current) {
                canvasSize.toDp() * minimumScale
            }),
            imageVector = buttonIcon,
            contentDescription = "Pulse Icon",
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FGPulsatingButtonPreview() {
    FGPulsatingButton(buttonColor = MaterialTheme.colors.primary)
}