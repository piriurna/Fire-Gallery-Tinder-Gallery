package com.artemissoftware.common.composables.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.composables.animations.models.PulsatingType
import kotlinx.coroutines.launch
import java.lang.IndexOutOfBoundsException

@Composable
fun FGPulsatingAnimation(
    pulseFraction: Float = 1.2f,
    type: PulsatingType = PulsatingType.INFINITE,
    content: @Composable () -> Unit
) {

    when(type){

        PulsatingType.INFINITE ->{
            PulsatingInfinite(pulseFraction = pulseFraction, content = content)
        }

        PulsatingType.LIMITED ->{
            PulsatingLimited(content = content)
        }
    }
}


@Composable
private fun AnimateValues(
    values: List<Float>,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> {

    val groups by rememberUpdatedState(newValue = values.zipWithNext())
    val state = remember { mutableStateOf(values.first()) }

    LaunchedEffect(key1 = groups) {
        val (_, setValue) = state
        animate(
            initialValue = 0f,
            targetValue = groups.size.toFloat(),
            animationSpec = animationSpec,
        ) { frame, _ ->
            try {
                val integerPart = frame.toInt()
                val (initialValue, finalValue) = groups[frame.toInt()]
                val decimalPart = frame - integerPart
                setValue(
                    initialValue + (finalValue - initialValue) * decimalPart
                )
            }catch (e: IndexOutOfBoundsException){}

        }
    }
    return state
}












@Composable
private fun PulsatingInfinite(
    pulseFraction: Float = 1.2f,
    content: @Composable () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition()

    val infiniteScaleAnimation by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.scale(infiniteScaleAnimation)) {
        content()
    }
}

@Preview
@Composable
private fun PulsatingInfinitePreview() {

    Column {
        FGPulsatingAnimation(
            type = PulsatingType.INFINITE,
            content = {
                Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "")
            }
        )
    }
}






@Composable
private fun PulsatingLimited(
    content: @Composable () -> Unit
) {

    val animationSpec = repeatable<Float>(
        iterations = 1,
        animation = tween(
            durationMillis = 3000,
            easing = LinearEasing,
        )
    )

    val animationList = listOf(1f, 1.2F, 1f, 1.2f, 1f, 1.2f, 1f, 1.2f, 1f)

    val animationScale by AnimateValues(
        values = animationList,
        animationSpec = animationSpec
    )

    Box(modifier = Modifier.scale(animationScale)) {
        content()
    }

}

@Preview
@Composable
private fun PulsatingLimitedPreview() {

    Column {
        FGPulsatingAnimation(
            type = PulsatingType.LIMITED,
            content = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
            }
        )
    }
}


@Preview
@Composable
private fun FGPulsatingAnimationPreview() {

    Column {
        FGPulsatingAnimation(
            type = PulsatingType.INFINITE,
            content = {
                Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "")
            }
        )
        FGPulsatingAnimation(
            type = PulsatingType.LIMITED,
            content = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
            }
        )
    }
}