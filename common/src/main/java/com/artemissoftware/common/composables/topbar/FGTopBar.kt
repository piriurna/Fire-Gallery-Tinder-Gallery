package com.artemissoftware.common.composables.topbar

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.button.FGCircularButton


//@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FGTopBar(
    modifier: Modifier = Modifier,
//    color: EDPAppBarColor = EDPAppBarColor.GREY,
//    title: String? = null,
//    subtitle: String? = null,
//    @DrawableRes navigationIconId: Int? = R.drawable.ic_arrow_left,
//    navigationText: String? = null,
//    onNavigationClick: (() -> Unit) = {},
//    @DrawableRes optionIconId: Int? = null,
//    optionText: String? = null,
//    onOptionClick: (() -> Unit) = {},
//    optionComposable: (@Composable BoxScope.() -> Unit)? = null,
//    isSearch: Boolean = false,
//    searchValue: String = "",
//    onSearchValue: (String) -> Unit = {}
) {
//    var isSearchMode by remember { mutableStateOf(false) }

    AppBar(
        modifier = modifier,
        backgroundColor = Color.Transparent,
    ) {
//        AnimatedVisibility(
//            modifier = Modifier.fillMaxHeight().align(Alignment.Center),
//            visible = !isSearchMode,
//            enter = slideInLeft(),
//            exit = slideOutLeft()
//        ) {
//            Column(
//                modifier = Modifier.fillMaxHeight().align(Alignment.Center),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                title?.let {
//                    EDPText(
//                        text = it,
//                        textAlign = TextAlign.Center,
//                        style = EDPStyle.TextPatronBold16,
//                        color = color.getTitleColor()
//                    )
//                }
//
//                subtitle?.let {
//                    EDPText(
//                        text = it,
//                        textAlign = TextAlign.Center,
//                        style = EDPStyle.Text,
//                        color = color.getSubtitleColor()
//                    )
//                }
//            }
//        }
//
//        if (isSearch) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterStart),
                visible = false,
                            enter = slideInLeft(),
            exit = slideOutLeft()
            ) {

        FGCircularButton(
            imageVector = Icons.Default.KeyboardArrowLeft,

        )

            }
//
//            AnimatedVisibility(
//                modifier = Modifier.fillMaxHeight().align(Alignment.Center),
//                visible = isSearchMode,
//                enter = slideInLeft(),
//                exit = slideOutLeft()
//            ) {
//                EDPSearchBar(
//                    value = searchValue,
//                    onValueChange = onSearchValue,
//                    onNavigationClick = {
//                        isSearchMode = false
//                        onSearchValue("")
//                    }
//                )
//            }
//
//        } else {
//            if (navigationIconId != null || navigationText != null) {
//                AppIconButton(
//                    modifier = Modifier.align(Alignment.CenterStart),
//                    iconId = navigationIconId,
//                    text = navigationText,
//                    onClick = onNavigationClick,
//                    color = color.getNavigationColor()
//                )
//            }
//
//            if (optionIconId != null || optionText != null) {
//                AppIconButton(
//                    modifier = Modifier.align(Alignment.CenterEnd),
//                    iconId = optionIconId,
//                    text = optionText,
//                    onClick = onOptionClick,
//                    color = color.getOptionColor()
//                )
//            }
//
//            optionComposable?.let {
//                it()
//            }
//        }
    }
}


@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        color = backgroundColor,
        contentColor = backgroundColor,
        elevation = 0.dp,
        shape = RectangleShape,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .height(56.dp),
            content = content
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FGTopBarPreview() {

    FGTopBar()
}



@Stable
@ExperimentalAnimationApi
fun slideInLeft(durationMillis: Int = 300): EnterTransition {
    return slideInHorizontally(animationSpec = tween(durationMillis))
}

@Stable
@ExperimentalAnimationApi
fun slideOutLeft(durationMillis: Int = 300): ExitTransition {
    return slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(durationMillis))
}