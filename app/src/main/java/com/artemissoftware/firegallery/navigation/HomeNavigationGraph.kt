package com.artemissoftware.firegallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.screens.gallery.GalleryScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
//    NavHost(
//        navController = navController,
//        route = Graph.HOME,
//        startDestination = BottomBarItem.Home.route
//    ) {
//
//        composable(route = BottomBarItem.Home.route) {
//
//            GalleryScreen()
//
//            NGGenericScreen(
//                name = BottomBarItem.Home.route,
//                onClick = {
//                    navController.navigate(Graph.DETAILS)
//                }
//            )
//        }
//
//    }
}