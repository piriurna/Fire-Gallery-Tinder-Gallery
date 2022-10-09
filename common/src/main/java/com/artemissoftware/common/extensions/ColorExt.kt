package com.artemissoftware.common.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import kotlin.math.ln


fun Color.withElevation(elevation: Dp): Color {
    val foreground = calculateForeground(elevation = elevation)
    return foreground.compositeOver(this)
}

private fun calculateForeground(elevation: Dp) : Color {
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return Color.White.copy(alpha = alpha)
}

fun String.hextoColor(): Color = Color(this.removePrefix("#").toLong(16) or 0x00000000FF000000)