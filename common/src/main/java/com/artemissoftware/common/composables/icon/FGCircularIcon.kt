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
    iconBackgroundColor: Color? = null,
    backgroundAlpha: Float? = null,
) {

    val backgroundColor = when{

        iconBackgroundColor != null && backgroundAlpha != null ->{
            iconBackgroundColor.copy(alpha = backgroundAlpha)
        }

        iconBackgroundColor != null && backgroundAlpha == null ->{
            iconBackgroundColor
        }

        iconBackgroundColor == null && backgroundAlpha != null ->{
            iconColor.copy(alpha = backgroundAlpha)
        }

        else  ->{
            Color.Transparent
        }
    }


    Icon(
        modifier = modifier
            .background(
                color = backgroundColor,
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
        FGCircularIcon(iconBackgroundColor = Color.Red, backgroundAlpha = 0.4F)
        FGCircularIcon(iconBackgroundColor = Color.Red)
        FGCircularIcon(backgroundAlpha = 0.1F)
        FGCircularIcon()
        FGCircularIcon(
            iconColor = Color.DarkGray,
            backgroundAlpha = 0.1F
        )
    }
}