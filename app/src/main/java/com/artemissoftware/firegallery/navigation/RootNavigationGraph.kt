package com.artemissoftware.firegallery.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.artemissoftware.common.composables.navigation.animatedComposable
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.screens.HomeScreen
import com.artemissoftware.firegallery.screens.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    scaffoldState: FGScaffoldState
) {

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
        animatedComposable(route = RootDestinationScreen.Splash.route) {
            SplashScreen(
                onAnimationFinish = {
                    navController.popBackStack()
                    navController.navigate(RootDestinationScreen.Home.route)

                }
            )
        }

        animatedComposable(route = RootDestinationScreen.Home.route) {
            HomeScreen(scaffoldState = scaffoldState)
        }
    }

}



sealed class RootDestinationScreen(val route: String) {
    object Home : RootDestinationScreen(route = "HOME")
    object Splash : RootDestinationScreen(route = "SPLASH")
}