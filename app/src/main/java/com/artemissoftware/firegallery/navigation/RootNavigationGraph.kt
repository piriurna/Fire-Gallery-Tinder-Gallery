package com.artemissoftware.firegallery.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.common.util.ScreenTransitions
import com.artemissoftware.common.util.ScreenTransitions.slideInLeft
import com.artemissoftware.common.util.ScreenTransitions.slideInRight
import com.artemissoftware.common.util.ScreenTransitions.slideOutLeft
import com.artemissoftware.common.util.ScreenTransitions.slideOutRight
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.screens.HomeScreen
import com.artemissoftware.firegallery.screens.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.NavGraphBuilder

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(navController: NavHostController, scaffoldState: FGScaffoldState) {

    AnimatedNavHost(
        navController = navController,
//        route = Graph.ROOT,
        startDestination = RootDestinationScreen.Splash.route
    ) {

        //--authenticationNavGraph(navController = navController)

//        splashComposable (
//            navigateToListScreen = {
//                navController.popBackStack()
//                navController.navigate(RootDestinationScreen.Home.route)
//
//            },
//        )

//        composableSlideHorizontal(
//            route = RootDestinationScreen.Home.route
//        ) {
//            HomeScreen(scaffoldState = scaffoldState)
//        }
//
        composableSlideHorizontal(
            route = RootDestinationScreen.Splash.route
        ) {
            SplashScreen(
                onAnimationFinish = {
                    navController.popBackStack()
                    navController.navigate(RootDestinationScreen.Home.route)

                }
            )
        }

        composableSlideHorizontal(
            route = RootDestinationScreen.Home.route
        ) {
            HomeScreen(scaffoldState = scaffoldState)
        }
    }

}

@ExperimentalAnimationApi
fun NavGraphBuilder.composableSlideHorizontal(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    durationMillis: Int = 3000,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content,
        enterTransition = {  slideInRight(durationMillis) },
        exitTransition = {              slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 3000)
            ) },
        popEnterTransition = {  slideInLeft(durationMillis) },
        popExitTransition = {  slideOutRight(durationMillis) },
    )
}


//@ExperimentalAnimationApi
//fun NavGraphBuilder.splashComposable(
//    navigateToListScreen: () -> Unit,
//) {
//    composable(
//        route = RootDestinationScreen.Splash.route,
//        exitTransition = { _, _ ->
//            slideOutVertically(
//                targetOffsetY = { fullHeight -> -fullHeight },
//                animationSpec = tween(durationMillis = 300)
//            )
//        }
//    ) {
//            SplashScreen(
//                onAnimationFinish = navigateToListScreen
//            )
//    }
//}

sealed class RootDestinationScreen(val route: String) {
    object Home : RootDestinationScreen(route = "HOME")
    object Splash : RootDestinationScreen(route = "SPLASH")
}