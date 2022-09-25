package com.artemissoftware.firegallery.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.common.composables.navigation.models.BaseDestinations
import com.artemissoftware.common.composables.navigation.models.CustomArguments
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.screens.picturedetail.PictureDetailScreen
import com.artemissoftware.firegallery.screens.pictures.PicturesScreen

fun NavGraphBuilder.galleryNavigationGraph(navController: NavHostController) {
    navigation(
        route = Graph.GALLERY,
        startDestination = GalleryDestinations.Pictures.route
    ) {

        composable(route = GalleryDestinations.Pictures.fullRoute, arguments = GalleryDestinations.Pictures.arguments) {
            PicturesScreen(navController)
        }

        composable(route = GalleryDestinations.PictureDetail.fullRoute, arguments = GalleryDestinations.Pictures.arguments) {
            PictureDetailScreen()
        }
    }
}

sealed class GalleryDestinations(
    val route: String,
    customArguments: List<CustomArguments> = emptyList()
) : BaseDestinations(route = route, customArguments = customArguments){

    object Pictures : GalleryDestinations(route = "PICTURES", listOf(CustomArguments(NavigationArguments.GALLERY_ID)))
    object PictureDetail : GalleryDestinations(route = "PICTURE_DETAIL", listOf(CustomArguments(NavigationArguments.PICTURE_ID)))
}