package com.artemissoftware.firegallery.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
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
        bottomBarItems = getBottomBarItems(scaffoldState = scaffoldState),
        navController = navController
    ) {
        HomeNavigationGraph(navController = navController, scaffoldState = scaffoldState)
    }

}


private fun getBottomBarItems(scaffoldState: FGScaffoldState) : List<BottomBarItem>{

    val items = listOf(
        HomeDestinations.Gallery, HomeDestinations.Favorites, HomeDestinations.Profile, HomeDestinations.Tinder
    )

    val bottomBarItems = mutableListOf<BottomBarItem>()

    items.forEach {

        when(it){

            HomeDestinations.Gallery->{
                bottomBarItems.add(
                    HomeDestinations.Gallery.toBottomBarItem(
                        title = "Gallery",
                        activeIcon = Icons.Default.Place,
                        inactiveIcon = Icons.Outlined.Place
                    )
                )
            }
            HomeDestinations.Favorites->{
                bottomBarItems.add(
                    HomeDestinations.Favorites.toBottomBarItem(
                        title = "Favorites",
                        activeIcon = Icons.Default.Favorite,
                        inactiveIcon = Icons.Outlined.Favorite
                    )
                )
            }
            HomeDestinations.Profile->{
                bottomBarItems.add(
                    HomeDestinations.Profile.toBottomBarItem(
                        title = "Profile",
                        activeIcon = Icons.Default.Person,
                        inactiveIcon = Icons.Outlined.Person
                    )
                )
            }
            HomeDestinations.Tinder->{
                bottomBarItems.add(
                    HomeDestinations.Tinder.toBottomBarItem(
                        title = "Tinder",
                        activeIcon = Icons.Default.Search,
                        inactiveIcon = Icons.Outlined.Search
                    )
                )
            }
            else -> {}
        }
    }

    return bottomBarItems
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {

    //HomeScreen()
}