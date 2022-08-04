package com.artemissoftware.firegallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.screens.gallery.GalleryScreen
import com.artemissoftware.firegallery.screens.home.models.BottomBarItem

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarItem.Gallery.route
    ) {

        composable(route = BottomBarItem.Gallery.route) {
            GalleryScreen(navController)
        }


        galleryNavigationGraph(navController)
    }
}