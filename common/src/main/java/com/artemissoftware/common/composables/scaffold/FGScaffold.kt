package com.artemissoftware.common.composables.scaffold

import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.R
import com.artemissoftware.common.composables.animations.FGLottieLoader
import com.artemissoftware.common.composables.dialog.DialogType
import com.artemissoftware.common.composables.dialog.FGDialog__a
import com.artemissoftware.common.composables.loading.FGLoading
import com.artemissoftware.common.composables.navigation.FGBottomNavigationBar
import com.artemissoftware.common.models.NavigationItem
import com.artemissoftware.common.theme.secondaryBackground

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
    isLoading: Boolean = false,
    @RawRes lottieId: Int = R.raw.gallery_photo,
//    showTopBar: Boolean = true,
    navController: NavHostController? = null,
    bottomBarItems: List<NavigationItem> = emptyList(),
    content: @Composable (PaddingValues) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .background(color.getBackgroundColor())
    ) {
        var scaffoldModifier = modifier.fillMaxSize()

//        var topBar: @Composable () -> Unit = {
//            EDPAppBar(
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
//        }
//
//        if (!showTopBar) {
//            scaffoldModifier = Modifier.padding(0.dp)
//            topBar = {}
//        }

        Scaffold(
            modifier = scaffoldModifier,
//            topBar = topBar,
            bottomBar = {
                navController?.let {
                    if(bottomBarItems.isNotEmpty()){
                        FGBottomNavigationBar(items = bottomBarItems, it)
                    }
                }
            },
//            snackbarHost = { state -> MySnackHost(state) },
            content = content
        )

        FGLoading(isLoading = isLoading)

        val successDialog = remember { mutableStateOf(true) }
        val dialogTypeSuccess = DialogType.Success(
            title =  "Get updates",
            description = "Allow permission to send notifications every day of the year",
            icon = Icons.Filled.Build
        )
        FGDialog__a(openDialogCustom = successDialog, dialogType = dialogTypeSuccess)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MySnackHost(state: SnackbarHostState) {
    SnackbarHost(
        state,
        snackbar = { data ->
            Snackbar(
                data,
                elevation = 1.dp
            )
        })
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