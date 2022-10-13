package com.artemissoftware.firegallery.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.navigation.mapper.toBottomBarItem
import com.artemissoftware.common.composables.navigation.models.BottomBarItem_
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.navigation.HomeDestinations
import com.artemissoftware.firegallery.navigation.HomeNavigationGraph
import com.artemissoftware.firegallery.screens.home.models.BottomBarItem
import com.artemissoftware.firegallery.screens.home.models.HomeTabs


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: FGScaffoldState
) {

    FGScaffold(
        fgScaffoldState = scaffoldState,
        bottomBarItems = HomeTabs.TABS,
        navController = navController
    ) {
        HomeNavigationGraph(navController = navController, scaffoldState = scaffoldState)
    }

}


private fun getBottomBarItems() : List<BottomBarItem_>{

    return listOf(
        HomeDestinations.Favorites.toBottomBarItem(
            title = "Favorites",
            activeIcon = Icons.Default.Favorite,
            inactiveIcon = Icons.Outlined.Favorite
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {

    //HomeScreen()
}