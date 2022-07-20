package com.artemissoftware.common.composables.chip

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.artemissoftware.common.extensions.withElevation
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import kotlin.math.ln



val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


@Stable
class Filter(
    val name: String,
    enabled: Boolean = false
) {
    val enabled = mutableStateOf(enabled)
}

val fruitFilters = listOf(
    Filter("Apple"),
    Filter("Bananas"),
    Filter("Cherries"),
    Filter("Damson"),
    Filter("Elderberry"),
    Filter("Finger"),
    Filter("Grapefruit"),
    Filter("Honeydew"),
    Filter("Indonesian"),
    Filter("Jack"),
    Filter("Kaffir"),
    Filter("Longan"),
    Filter("Mandarin"),
    Filter("Navel"),
    Filter("Oval")
)




@Composable
fun ChipSurface(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    color: Color = Purple500,
    contentColor: Color = Purple200,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp
) {

    val internalBorder = Modifier.fadeInDiagonalGradientBorder(
        showBorder = true,
        colors = listOf(
            Purple500, Purple200
        ),
        shape = shape
    )

    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .zIndex(elevation.value)
            .then(if (border != null) Modifier.border(border, shape = shape) else internalBorder)
            .background(
                color = getBackgroundColorForElevation(color = color, elevation = elevation),
                shape = shape
            )
            .clip(shape = shape)
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor,
            content = {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                ) {

                    Text(
                        text = "filter.name",
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                            .fillMaxHeight()
                    )
                }
            }
        )
    }
}




@Composable
fun FilterChip(
    filter: Filter,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape
) {
    val (selected, setSelected) = filter.enabled
    val backgroundColor by animateColorAsState(
        if (selected) Purple500 else Color.White
    )

    val textColor by animateColorAsState(
        if (selected) Color.White else Color.Black
    )

    ChipSurface(
        modifier = modifier.height(40.dp),
        color = backgroundColor,
        contentColor = textColor,
        shape = shape,
        elevation = 2.dp,
    )
}

@Preview(showBackground = true)
@Composable
private fun FilterChipPreview() {

    FilterChip(filter = fruitFilters[0])
}





@Composable
private fun getBackgroundColorForElevation(color: Color, elevation: Dp) : Color {
    return if (elevation > 0.dp) {
        color.withElevation(elevation = elevation)
    } else {
        color
    }
}


@Preview(showBackground = true)
@Composable
private fun ChipSurfacePreview() {
    ChipSurface(
        shape = CircleShape
    )
}






















/*
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
            .background(
                color = getBackgroundColorForElevation(color = color, elevation = elevation),
                shape = shape
            )
            .clip(shape = shape)
    ) {
        CompositionLocalProvider(/*LocalContentColor provides contentColor,*/ content = content)
    }
}





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
            elevation = 8.dp,
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
                        .padding(horizontal = 20.dp, vertical = 6.dp)
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


        ChipSurface(
            modifier = Modifier.padding(end = 4.dp, bottom = 8.dp).height(40.dp),
            color = Color.Green,
//            contentColor = textColor,
            shape = MaterialTheme.shapes.small,
            elevation = 2.dp
        ) {
//            val interactionSource = remember { MutableInteractionSource() }
//
//            val pressed by interactionSource.collectIsPressedAsState()
//            val backgroundPressed =
//                if (pressed) {
//                    Modifier.offsetGradientBackground(
//                        listOf(
//                            Purple500, Purple200
//                        ),
//                        200f,
//                        0f
//                    )
//                } else {
//                    Modifier.background(Color.Transparent)
//                }
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
                    .height(40.dp)
            ) {
                Text(
                    text = "filter.name",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}

 */




//@Composable
//fun FilterChipSection(
//    filters: List<Filter>
//) {
//    val context = LocalContext.current
//
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(55.dp)
//                .background(MaterialTheme.colors.primary),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Compose Chip Groups",
//                color = Color.White,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
//        }
//
//        Column(
//            modifier = Modifier.padding(10.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            FlowRow(
//                mainAxisAlignment = FlowMainAxisAlignment.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 12.dp, bottom = 16.dp)
//                    .padding(horizontal = 4.dp)
//            ) {
//                filters.forEach { filter ->
//                    FilterChip(
//                        filter = filter,
//                        modifier = Modifier.padding(end = 4.dp, bottom = 8.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(15.dp))
//
//            Button(
//                onClick = {
//                    fruitFilters.forEach { fruit ->
//                        if (fruit.enabled.value) {
//                            Toast.makeText(context, fruit.name, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                },
//                modifier = Modifier
//                    .clip(RoundedCornerShape(5.dp))
//            ) {
//                Text(
//                    text = "Submit",
//                    modifier = Modifier.padding(5.dp)
//                )
//            }
//        }
//    }
//}
//
//
//
//@Composable
//private fun getBackgroundColorForElevation(color: Color, elevation: Dp) : Color {
//    return if (elevation > 0.dp) {
//        color.withElevation(elevation = elevation)
//    } else {
//        color
//    }
//}

//fun Modifier.offsetGradientBackground(
//    colors: List<Color>,
//    width: Float,
//    offset: Float = 0f
//) = background(
//    Brush.horizontalGradient(
//        colors,
//        startX = -offset,
//        endX = width - offset,
//        tileMode = TileMode.Mirror
//    )
//)

fun Modifier.fadeInDiagonalGradientBorder(
    showBorder: Boolean,
    colors: List<Color>,
    borderSize: Dp = 2.dp,
    shape: Shape
) = composed {
    val animatedColors = List(colors.size) { i ->
        animateColorAsState(if (showBorder) colors[i] else colors[i].copy(alpha = 0f)).value
    }
    diagonalGradientBorder(
        colors = animatedColors,
        borderSize = borderSize,
        shape = shape
    )
}

fun Modifier.diagonalGradientBorder(
    colors: List<Color>,
    borderSize: Dp = 2.dp,
    shape: Shape
) = border(
    width = borderSize,
    brush = Brush.linearGradient(colors),
    shape = shape
)


//
////@Preview(showBackground = true)
////@Composable
////fun DefaultPreview() {
////    FilterChipSection(
////        filters = fruitFilters
////    )
////}
//
//
////---------------------------
//
//
//
//
//
////---------------------------
////---------------------------
//
