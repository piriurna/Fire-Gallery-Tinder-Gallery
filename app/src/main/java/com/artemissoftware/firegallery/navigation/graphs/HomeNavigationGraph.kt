package com.artemissoftware.firegallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.common.composables.navigation.models.BaseDestinations
import com.artemissoftware.common.composables.navigation.models.CustomArguments
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.navigation.graphs.galleryNavigationGraph
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.screens.favorites.FavoritesScreen
import com.artemissoftware.firegallery.screens.gallery.GalleryScreen
import com.artemissoftware.firegallery.screens.home.models.BottomBarItem
import com.artemissoftware.firegallery.screens.profile.ProfileScreen

@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
    scaffoldState: FGScaffoldState
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarItem.Gallery.route
    ) {

        composable(route = BottomBarItem.Gallery.route) {
            GalleryScreen(navController, scaffoldState = scaffoldState)
        }

        composable(route = BottomBarItem.Favorites.route) {
            FavoritesScreen(navController = navController, scaffoldState = scaffoldState)
        }

        composable(route = BottomBarItem.Profile.route) {
            ProfileScreen(scaffoldState)
        }

        galleryNavigationGraph(navController = navController, scaffoldState = scaffoldState)

    }
}


sealed class HomeDestinations(
    val route: String,
    customArguments: List<CustomArguments> = emptyList()
) : BaseDestinations(route = route, customArguments = customArguments){

    object Favorites : HomeDestinations(route = "FAVORITES")
}