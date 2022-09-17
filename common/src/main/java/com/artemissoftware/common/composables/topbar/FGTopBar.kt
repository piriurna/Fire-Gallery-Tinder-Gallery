package com.artemissoftware.common.composables.topbar

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.button.FGCircularButton
import com.artemissoftware.common.composables.chip.Purple200
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.theme.FGStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FGTopBar_(
    modifier: Modifier = Modifier.padding(top = 20.dp),
//    color: EDPAppBarColor = EDPAppBarColor.GREY,
//    title: String? = null,
//    subtitle: String? = null,
//    @DrawableRes navigationIconId: Int? = R.drawable.ic_arrow_left,
//    navigationText: String? = null,
//    onNavigationClick: (() -> Unit) = {},
//    @DrawableRes optionIconId: Int? = null,
//    optionText: String? = null,
//    onOptionClick: (() -> Unit) = {},
    optionComposable: (@Composable BoxScope.() -> Unit)? = null,
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
                visible = true,
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
                .padding(horizontal = 16.dp)
                .height(56.dp),
            content = content
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FGTopBar(
    modifier: Modifier = Modifier.padding(top = 0.dp),
    backgroundColor: Color = Color.Transparent,
    title: String? = null,
    subTitle: String? = null,
    isVisible: Boolean = false,
    onNavigationClick: (() -> Unit),
    optionComposable: (@Composable BoxScope.() -> Unit)? = null,
) {

    val animationTime = 300

    AppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
    ) {

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(
                initialOffsetX = { -3000 }, // small slide 300px
                animationSpec = tween(
                    durationMillis = animationTime,
                    easing = LinearEasing // interpolator
                )
            ),
            exit = fadeOut(),
            modifier = Modifier,
        ) {

            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FGCircularButton(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    modifier = Modifier,
                    onClick = onNavigationClick
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5F)
                        .padding(start = 16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    title?.let {

                        FGText(
                            text = it,
                            style = FGStyle.TextAlbertSansBold16,
                            textAlign = TextAlign.Start
                        )
                    }


                    subTitle?.let {
                        FGText(
                            text = it,
                            style = FGStyle.TextAlbertSansMedium,
                            textAlign = TextAlign.Start
                        )
                    }
                }


                Box(modifier = Modifier.weight(0.1F)) {
                    optionComposable?.let {
                        it()
                    }
                }


            }

        }

//        FGCircularButton(
//            imageVector = Icons.Default.KeyboardArrowLeft,
//            modifier = Modifier.align(Alignment.CenterStart),
//            onClick= onNavigationClick
//        )
//
//
////        AnimatedVisibility(
////            modifier = Modifier.fillMaxHeight().align(Alignment.Center),
////            visible = true,
////            enter = slideInLeft(),
////            exit = slideOutLeft()
////        ) {
//            Column(
//                modifier = Modifier.fillMaxHeight().align(Alignment.Center),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.Center
//            ) {
//
//                title?.let {
//
//                    FGText(
//                        text = it,
//                        style = FGStyle.TextAlbertSansBold,
//                        textAlign = TextAlign.Start
//                    )
//                }
//
//
//                subTitle?.let {
//                    FGText(
//                        text = it,
//                        style = FGStyle.TextAlbertSansMedium,
//                        textAlign = TextAlign.Start
//                    )
//                }
//
//
//            }
////        }
//
//
//        optionComposable?.let {
//            it()
//        }
//





    }









}


//
//@Preview(showBackground = true)
//@Composable
//private fun FGTopBar_1_Preview() {
//
//    FGTopBar(
//        onNavigationClick = {},
//        optionComposable = {}
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun FGTopBar_2_Preview() {
//
//    FGTopBar(
//        title = "Title",
//        subTitle = "subTitle",
//        onNavigationClick = {},
//        optionComposable = {}
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun FGTopBar_3_Preview() {
//
//    FGTopBar(
//        onNavigationClick = {},
//        optionComposable = {
//            FGCircularButton(
//                imageVector = Icons.Default.KeyboardArrowLeft,
//                modifier = Modifier.align(Alignment.CenterEnd)
//            )
//        }
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun FGTopBar_4_Preview() {
//
//    FGTopBar(
//        title = "Title",
//        onNavigationClick = {},
//        optionComposable = {
//            FGCircularButton(
//                imageVector = Icons.Default.KeyboardArrowLeft,
//                modifier = Modifier.align(Alignment.CenterEnd)
//            )
//        }
//    )
//}


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






enum class AlertState {
    Collapsed,
    Expanded
}

//@Preview
//@Composable
//fun HomeScreen() {
//    val scope = rememberCoroutineScope()
//    val alertHeight = 40.dp
//    var currentState by remember { mutableStateOf(AlertState.Collapsed) }
//    val transition = updateTransition(currentState, label = "")
//    val alertOffset by transition.animateDp(label = "") {
//        when (it) {
//            AlertState.Collapsed -> -alertHeight
//            AlertState.Expanded -> 0.dp
//        }
//    }
//    Column(Modifier.fillMaxHeight()) {
//
//        Column(
//            modifier = Modifier.clipToBounds().offset(y = alertOffset)
//        ) {
//            Row(
//                Modifier.height(alertHeight).fillMaxWidth().background(Color.Red).padding(start = 16.dp)
//            ) {
//                Text(
//                    "An error has occurred", style = TextStyle(Color.White),
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
//            Button(modifier = Modifier.padding(16.dp), onClick = {
//                scope.launch {
//                    //currentState = AlertState.Expanded
//                    delay(100)
//                    currentState = if(currentState == AlertState.Expanded) AlertState.Collapsed else AlertState.Expanded
//                }
//            }) {
//                Text("Show Alert" + "  - " + alertOffset.value + "...." + currentState)
//            }
//        }
//    }
//}
//
//@Preview
//@ExperimentalAnimationApi
//@Composable
//fun MainContent(){
//    val longtext = "Lorem Ipsum is simply dummy text of the printing" +
//            " and typesetting industry. Lorem Ipsum has been the" +
//            " industry's standard dummy text ever since the 1500s," +
//            " when an unknown printer took a galley of type and" +
//            " scrambled it to make a type specimen book."
//
//    val isVisible = remember { mutableStateOf(true) }
//
//    Column(
//        modifier = Modifier
//            .background(Color(0xFFF5F5F5))
//            .padding(16.dp)
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ){
//        Button(
//            onClick = {
//                isVisible.value = !isVisible.value
//            },
//        ) {
//            Text(if (isVisible.value)"Hide" else "Show")
//        }
//        val animationTime = 300
//
//        AnimatedVisibility(
//            visible = isVisible.value,
//            enter = slideInHorizontally(
//                initialOffsetX = { -3000 }, // small slide 300px
//                animationSpec = tween(
//                    durationMillis = animationTime,
//                    easing = LinearEasing // interpolator
//                )
//            ),
//            exit = fadeOut(),
//        ) {
//            Box(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(Color(0xFFD2691E))
//                    .padding(16.dp)
//                    .fillMaxSize()
//            ) {
//                Text(
//                    text = longtext,
//                    fontSize = 35.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center,
//                    fontFamily = FontFamily.Cursive,
//                    color = Color(0xFF58111A),
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            }
//        }
//    }
//}




//------------------------------------------------------------------------



@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val alertHeight = 56.dp
    var currentState by remember { mutableStateOf(AlertState.Collapsed) }
    val transition = updateTransition(currentState, label = "")
    val alertOffset by transition.animateDp(label = "") {
        when (it) {
            AlertState.Collapsed -> -alertHeight
            AlertState.Expanded -> 0.dp
        }
    }

    val size: Dp by animateDpAsState(
        targetValue = if (currentState == AlertState.Expanded) 0.dp else 56.dp,
        animationSpec = tween(
            durationMillis = 300, // duration
            delayMillis = 200, // delay before start animation
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            // disable the button
            //isEnabled.value = true
        }
    )



    Column(Modifier.fillMaxHeight()) {
//
//        Column(
//            modifier = Modifier
//                .clipToBounds()
//                .offset(y = alertOffset)
//        ) {
//
//            AppBar(
//                backgroundColor = Color.Red,
//                modifier = Modifier.height(alertHeight)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxHeight(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    FGCircularButton(
//                        imageVector = Icons.Default.KeyboardArrowLeft,
//                        modifier = Modifier,
//                        onClick = {  }
//                    )
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxHeight()
//                            .weight(0.5F)
//                            .padding(start = 16.dp),
//                        horizontalAlignment = Alignment.Start,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//
//                        "title"?.let {
//
//                            FGText(
//                                text = it,
//                                style = FGStyle.TextAlbertSansBold16,
//                                textAlign = TextAlign.Start
//                            )
//                        }
//
//
//                        "subTitle"?.let {
//                            FGText(
//                                text = it,
//                                style = FGStyle.TextAlbertSansMedium,
//                                textAlign = TextAlign.Start
//                            )
//                        }
//                    }
//
//
////                    Box(modifier = Modifier.weight(0.1F)) {
////                        optionComposable?.let {
////                            it()
////                        }
////                    }
//
//
//                }
//            }
//
//
//        }



        val animationTime = 300
        AppBar(
            backgroundColor = Color.Red,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(
                    visible = kiki(currentState),
                    enter = slideInHorizontally(
                        initialOffsetX = { -300 }, // it == fullWidth
                        animationSpec = tween(
                            durationMillis = animationTime,
                            easing = LinearEasing
                        ),
                    ),
                    exit = slideOutLeft()
                ) {

                    FGCircularButton(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        modifier = Modifier,
                        onClick = { }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5F)
                        .padding(start = 16.dp).offset(y = alertOffset).alpha(0.4F),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    "title"?.let {

                        FGText(
                            text = it,
                            style = FGStyle.TextAlbertSansBold16,
                            textAlign = TextAlign.Start
                        )
                    }


                    "subTitle"?.let {
                        FGText(
                            text = it,
                            style = FGStyle.TextAlbertSansMedium,
                            textAlign = TextAlign.Start
                        )
                    }
                }


                    Box(modifier = Modifier
                        .weight(0.1F).offset(y = size)
                    ) {
                        FGCircularButton(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            modifier = Modifier,
                            onClick = { }
                        )
                    }


            }
        }

        Button(modifier = Modifier.padding(16.dp), onClick = {
            scope.launch {
                //currentState = AlertState.Expanded
                delay(100)
                currentState = if(currentState == AlertState.Expanded) AlertState.Collapsed else AlertState.Expanded
            }
        }) {
            Text("Show Alert" + "  - " + alertOffset.value + "...." + currentState)
        }
    }
}

fun kiki (currentState: AlertState): Boolean {
    return !if(currentState == AlertState.Expanded) false else true
}



