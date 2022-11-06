package com.artemissoftware.common.composables.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.common.extensions.DrawCircleOnCanvas

@Composable
fun FGPulsatingButton(
    modifier : Modifier = Modifier,
    buttonColor : Color,
    pulseColor : Color = buttonColor,
    shouldAnimate : Boolean = true,
    animationDurationMillis : Int= 1500,
    onClick : () -> Unit,
    centerAppLogo : @Composable () -> Unit,
) {

    BoxWithConstraints(
        modifier = modifier.clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if(shouldAnimate) {
            DrawCircleOnCanvas(
                circleSize = with(LocalDensity.current) {
                    constraints.maxWidth.toDp()
                },
                color = pulseColor,
                animationDurationMillis = animationDurationMillis,
                initialScale = 0.4f
            )

            DrawCircleOnCanvas(
                circleSize = with(LocalDensity.current) {
                    constraints.maxWidth.toDp()
                },
                color = pulseColor,

                baseDelay = animationDurationMillis / 2,
                animationDurationMillis = animationDurationMillis,
                initialScale = 0.4f
            )

            DrawCircleOnCanvas(
                circleSize = with(LocalDensity.current) {
                    constraints.maxWidth.toDp()
                },
                color = pulseColor,
                baseDelay = animationDurationMillis / 4,
                animationDurationMillis = animationDurationMillis,
                initialScale = 0.4f
            )


        }
        val buttonModifier = if(shouldAnimate) Modifier.clip(CircleShape) else Modifier
        Box(
            modifier = buttonModifier.clickable(onClick = onClick)
        ) {
            centerAppLogo()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FGPulsatingButtonPreview() {
    FGPulsatingButton(buttonColor = MaterialTheme.colors.primary, centerAppLogo = {}, onClick = {})
}