package com.artemissoftware.common.composables.animations

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FlipAnimation(
    modifier: Modifier = Modifier,
    flip: Boolean,
    content: @Composable () -> Unit
) {

    val animationRotation by animateFloatAsState(
        targetValue = if (flip) 180f else 0f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    Box(
        modifier = modifier
            .graphicsLayer { rotationY = animationRotation },
        contentAlignment = Alignment.Center,
    ) {
        content.invoke()
    }
}

@Preview(showBackground = false)
@Composable
private fun FlipAnimationPreview() {

    Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
        FlipAnimation(
            content = {
                Icon(
                    rememberVectorPainter(image = /*if (animationRotation > 90f) activeIcon else inactiveIcon*/Icons.Filled.Home),
                    contentDescription = "contentDescription",
                )
            },
            flip = true
        )

        FlipAnimation(
            content = {
                Icon(
                    rememberVectorPainter(image = /*if (animationRotation > 90f) activeIcon else inactiveIcon*/Icons.Outlined.Home),
                    contentDescription = "contentDescription",
                )
            },
            flip = false
        )

    }
}