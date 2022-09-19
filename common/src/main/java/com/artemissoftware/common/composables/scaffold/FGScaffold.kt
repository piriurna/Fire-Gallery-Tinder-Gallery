package com.artemissoftware.common.composables.scaffold

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.R
import com.artemissoftware.common.composables.dialog.FGDialog
import com.artemissoftware.common.composables.loading.FGLoading
import com.artemissoftware.common.composables.navigation.FGBottomNavigationBar
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.common.models.NavigationItem
import kotlin.math.roundToInt

@Composable
fun FGScaffold(
    modifier: Modifier = Modifier/*.statusBarsPadding()*/,
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
//    isSearchAppBar: Boolean = false,
//    searchValue: String = "",
//    onSearchValue: (String) -> Unit = {},
    fgScaffoldState: FGScaffoldState? = null,
    isLoading: Boolean = false,
    @RawRes lottieId: Int = R.raw.gallery_photo,
//    showTopBar: Boolean = true,
    navController: NavHostController? = null,
    bottomBarItems: List<NavigationItem> = emptyList(),
    content: @Composable (PaddingValues) -> Unit,
) {

    val bottomBarHeight = 64.dp
    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }



    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y

                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)


                return Offset.Zero
            }
        }
    }



    Box(
        modifier = Modifier.nestedScroll(nestedScrollConnection)
            .fillMaxSize()
//            .background(color.getBackgroundColor())
    ) {
        var scaffoldModifier = modifier.fillMaxSize()

        var topBar: @Composable () -> Unit = {
//            FGTopBar(
//                color = color,
//                title = title,
//                subtitle = subtitle,
//                navigationIconId = navigationIconId,
//                navigationText = navigationText,
//                onNavigationClick = onNavigationClick,
//                optionIconId = optionIconId,
//                optionText = optionText,
//                onOptionClick = onOptionClick,
//                optionComposable = optionComposable,
//                isSearch = isSearchAppBar,
//                searchValue = searchValue,
//                onSearchValue = onSearchValue
//            )
        }

//        if (!showTopBar) {
//            scaffoldModifier = Modifier.padding(0.dp)
//            topBar = {}
//        }

        Scaffold(
            modifier = scaffoldModifier,
//            topBar = topBar,
            bottomBar = {


                            FGBottomNavigationBar(
                                modifier = Modifier
                                    .height(bottomBarHeight)
                                    .offset {
                                        IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt())
                                    },
                                items = bottomBarItems,
                                navController = navController
                            )


            },
//            snackbarHost = { state -> MySnackHost(state) },
            content = content
        )

        topBar.invoke()

        FGLoading(isLoading = isLoading)

        fgScaffoldState?.let { FGDialog(fgScaffoldState = it) }

    }
}



@Preview(showBackground = true)
@Composable
private fun FGScaffoldPreview() {
    FGScaffold(
        navController = rememberNavController(),
        bottomBarItems = listOf(MockNavigationBar.Create, MockNavigationBar.Profile),
        content = {

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Text")
            }

        }
    )
}

private sealed class MockNavigationBar(
    title: String,
    activeIcon: ImageVector,
    inactiveIcon: ImageVector,
    route: String,
) : NavigationItem(title, activeIcon, inactiveIcon, route) {
    object Create: MockNavigationBar("Create", Icons.Filled.Create, Icons.Outlined.Create, "Create")
    object Profile: MockNavigationBar("Profile", Icons.Filled.Person, Icons.Outlined.Person, "Profile")
}