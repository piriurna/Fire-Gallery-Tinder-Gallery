package com.artemissoftware.firegallery.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.navigation.mapper.toBottomBarItem
import com.artemissoftware.common.composables.navigation.models.BottomBarItem
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.navigation.HomeDestinations
import com.artemissoftware.firegallery.navigation.HomeNavigationGraph


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: FGScaffoldState
) {

    FGScaffold(
        fgScaffoldState = scaffoldState,
        bottomBarItems = getBottomBarItems(),
        navController = navController
    ) {
        HomeNavigationGraph(navController = navController, scaffoldState = scaffoldState)
    }

}


private fun getBottomBarItems() : List<BottomBarItem>{

    return listOf(
        HomeDestinations.Gallery.toBottomBarItem(
            title = "Gallery",
            activeIcon = Icons.Default.Place,
            inactiveIcon = Icons.Outlined.Place
        ),
//        HomeDestinations.Favorites.toBottomBarItem(
//            title = "Favorites",
//            activeIcon = Icons.Default.Favorite,
//            inactiveIcon = Icons.Outlined.Favorite
//        ),
//        HomeDestinations.Profile.toBottomBarItem(
//            title = "Profile",
//            activeIcon = Icons.Default.Person,
//            inactiveIcon = Icons.Outlined.Person
//        )
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {

    //HomeScreen()
}