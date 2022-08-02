package com.artemissoftware.common.composables.scaffold

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.composables.navigation.FGBottomNavigationBar
import com.artemissoftware.common.models.NavigationItem

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
//    showTopBar: Boolean = true,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
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
            bottomBar = bottomBar,
            content = content
        )

//        AnimatedVisibility(
//            modifier = Modifier.fillMaxSize(),
//            visible = isShowLoading
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clickable(enabled = false, onClick = {})
//                    .background(MaterialTheme.colors.secondaryBackground.copy(alpha = 0.8f)),
//                contentAlignment = Alignment.Center
//            ) {
//                EDPLoading(
//                    modifier = Modifier.size(128.dp),
//                    resId = R.raw.lottie_loading
//                )
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FGScaffoldPreview() {
    FGScaffold(
        bottomBar = {FGBottomNavigationBar(items = listOf(NavigationItem.Home, NavigationItem.Settings)) },
        content = {

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Text")
            }

        }
    )
}