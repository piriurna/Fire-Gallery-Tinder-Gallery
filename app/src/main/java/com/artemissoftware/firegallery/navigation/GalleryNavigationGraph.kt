package com.artemissoftware.firegallery.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.screens.picturedetail.PictureDetailScreen
import com.artemissoftware.firegallery.screens.pictures.PicturesScreen

fun NavGraphBuilder.galleryNavigationGraph(navController: NavHostController) {
    navigation(
        route = Graph.GALLERY,
        startDestination = GalleryDestinations.Pictures.route
    ) {

        composable(route = GalleryDestinations.Pictures.route) {
            PicturesScreen(navController)
        }

        composable(route = GalleryDestinations.PictureDetail.route) {
            PictureDetailScreen()
        }
    }
}

sealed class GalleryDestinations(val route: String) {
    object Pictures : GalleryDestinations(route = "PICTURES")
    object PictureDetail : GalleryDestinations(route = "PICTURE_DETAIL")
}