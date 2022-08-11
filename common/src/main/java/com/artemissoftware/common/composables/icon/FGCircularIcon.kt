package com.artemissoftware.common.composables.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FGCircularIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.LocationOn,
    iconColor: Color = Color.Green,
    iconBackgroundColor: Color = Color.Blue,
) {

    Icon(
        modifier = modifier
            .background(
                color = iconBackgroundColor,
                shape = CircleShape
            )
            .padding(8.dp),
        imageVector = icon,
        tint = iconColor,
        contentDescription = "",
    )
}

@Preview
@Composable
fun FGCircularIconPreview() {


    Column {
        FGCircularIcon()
        FGCircularIcon()
    }
}