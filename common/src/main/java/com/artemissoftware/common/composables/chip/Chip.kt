package com.artemissoftware.common.composables.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun ChipSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = Color.Red,
//    contentColor: Color = Purple200,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .zIndex(elevation.value)
            .then(if (border != null) Modifier.border(border, shape = shape) else Modifier)
//            .background(
//                color = getBackgroundColorForElevation(color = color, elevation = elevation),
//                shape = shape
//            )
//            .clip(shape = shape)
    ) {
        CompositionLocalProvider(/*LocalContentColor provides contentColor,*/ content = content)
    }
}


//@Composable
//private fun getBackgroundColorForElevation(color: Color, elevation: Dp) : Color {
//    return if (elevation > 0.dp) {
//        color.withElevation(elevation = elevation)
//    } else {
//        color
//    }
//}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ChipSurface(){
            Box(
                modifier = Modifier
//                    .toggleable(
//                        value = selected,
//                        onValueChange = setSelected,
//                        interactionSource = interactionSource,
//                        indication = null
//                    )
//                    .then(backgroundPressed)
//                    .then(border)
//                    .height(40.dp)
            ) {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
//                        .padding(horizontal = 20.dp, vertical = 6.dp)
//                        .fillMaxHeight()
                )
            }
        }

        ChipSurface(
            elevation = 3.dp,
            border = BorderStroke(1.dp, Color.Blue)
        ){
            Box(
                modifier = Modifier
//                    .toggleable(
//                        value = selected,
//                        onValueChange = setSelected,
//                        interactionSource = interactionSource,
//                        indication = null
//                    )
//                    .then(backgroundPressed)
//                    .then(border)
//                    .height(40.dp)
            ) {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
//                        .padding(horizontal = 20.dp, vertical = 6.dp)
//                        .fillMaxHeight()
                )
            }
        }

        ChipSurface(
            elevation = 0.dp
        ){
            Box(
                modifier = Modifier
//                    .toggleable(
//                        value = selected,
//                        onValueChange = setSelected,
//                        interactionSource = interactionSource,
//                        indication = null
//                    )
//                    .then(backgroundPressed)
//                    .then(border)
//                    .height(40.dp)
            ) {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
//                        .padding(horizontal = 20.dp, vertical = 6.dp)
//                        .fillMaxHeight()
                )
            }
        }
    }
}