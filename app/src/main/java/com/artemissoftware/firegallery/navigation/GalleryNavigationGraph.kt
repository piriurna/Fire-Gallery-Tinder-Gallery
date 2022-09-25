package com.artemissoftware.firegallery.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.common.composables.navigation.models.BaseDestinations
import com.artemissoftware.common.composables.navigation.models.CustomArguments
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.navigation.navtypes.GalleryUINavType
import com.artemissoftware.firegallery.screens.picturedetail.PictureDetailScreen
import com.artemissoftware.firegallery.screens.pictures.PicturesScreen

fun NavGraphBuilder.galleryNavigationGraph(
    navController: NavHostController,
    scaffoldState: FGScaffoldState
) {
    navigation(
        route = Graph.GALLERY,
        startDestination = GalleryDestinations.Pictures.route
    ) {

        composable(route = GalleryDestinations.Pictures.fullRoute, arguments = GalleryDestinations.Pictures.arguments){
            PicturesScreen(navController = navController, scaffoldState = scaffoldState)
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

    object Pictures : GalleryDestinations(route = "PICTURES", listOf(CustomArguments(key = NavigationArguments.GALLERY_ID, type = GalleryUINavType()  )))
    object PictureDetail : GalleryDestinations(route = "PICTURE_DETAIL", listOf(CustomArguments(NavigationArguments.PICTURE_ID)))
}