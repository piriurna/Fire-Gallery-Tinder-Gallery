package com.artemissoftware.firegallery.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.MutableState
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.artemissoftware.common.composables.navigation.animatedComposable
import com.artemissoftware.common.composables.navigation.models.BaseDestinations
import com.artemissoftware.common.composables.navigation.models.CustomArguments
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.DetailsScreen
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.navigation.navtypes.GalleryUINavType
import com.artemissoftware.firegallery.screens.picturedetail.PictureDetailScreen
import com.artemissoftware.firegallery.screens.pictures.PicturesScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.galleryNavigationGraph(
    navController: NavHostController,
    scaffoldState: FGScaffoldState,
    dplink: Boolean = false
) {


    navigation(
        route = Graph.GALLERY,
        startDestination = GalleryDestinations.Pictures.route
    ) {


//        composable(
//            route = GalleryDestinations.Details.route,
//            //arguments = GalleryDestinations.Pictures.arguments,
//            //--deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}" })
//        ){
//            DetailsScreen(message = "message")
//        }


//        composable(
//            route = GalleryDestinations.Details.fullRoute,
//            arguments = listOf(navArgument(MY_ARG) { type = NavType.StringType }),
//            deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}" })
//        ) {
//            val arguments = it.arguments
//            arguments?.getString(MY_ARG)?.let { message ->
//                DetailsScreen(message = message)
//            }
//        }

//        animatedComposable(
//            route = GalleryDestinations.Details.route,
//            arguments = listOf(navArgument(MY_ARG) { type = NavType.StringType }),
//            deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}" })
//        ) {
//            val arguments = it.arguments
//            arguments?.getString(MY_ARG)?.let { message ->
//                DetailsScreen(message = message)
//            }
//        }



        composable(
            route = GalleryDestinations.Pictures.fullRoute,
            arguments = GalleryDestinations.Pictures.arguments,
        ){

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
    object Details : GalleryDestinations(route = "DETAIL")
}

fun NavGraphBuilder.loloNavigationGraph(
    navController: NavHostController,
    scaffoldState: FGScaffoldState,
    startDestination: MutableState<String>
) {
    navigation(
        startDestination = LoloDestinations.Details.route,
        route = "nested_graph_route"
    ) {

        composable(
            route = LoloDestinations.Details.route,
            arguments = listOf(navArgument(MY_ARG) { type = NavType.StringType }, navArgument(
                MY_LOLO) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}&$MY_LOLO={$MY_LOLO}" })
        ) {

            startDestination.value = RootDestinations.Home.route

            val arguments = it.arguments
            val my_arg = arguments?.getString(MY_ARG)?: "SEM my_arg"
            val my_lolo = arguments?.getString(MY_LOLO)?: "SEM my_lolo"

                DetailsScreen(message = "Details1 $my_arg ++++ $my_lolo")

        }

        composable(
            route = LoloDestinations.Details2.route,
            arguments = listOf(navArgument(MY_ARG) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}" })
        ) {

            startDestination.value = RootDestinations.Home.route

            val arguments = it.arguments
            val my_arg = arguments?.getString(MY_ARG)?: "SEM my_arg"
            val my_lolo = arguments?.getString(MY_LOLO)?: "SEM my_loloFIsj"

            DetailsScreen(message = "Details2 $my_arg ++++ $my_lolo")

        }
    }
}


sealed class LoloDestinations(
    val route: String,
    customArguments: List<CustomArguments> = emptyList()
) : BaseDestinations(route = route, customArguments = customArguments){

    object Details : RootDestinations(route = "DETAIL")
    object Details2 : RootDestinations(route = "DETAIL2")
}