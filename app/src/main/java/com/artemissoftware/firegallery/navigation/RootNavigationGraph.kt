package com.artemissoftware.firegallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.navigation.models.Graph
import com.artemissoftware.firegallery.screens.HomeScreen
import com.artemissoftware.firegallery.screens.SplashScreen
import com.artemissoftware.firegallery.screens.pictures.PicturesScreen

@Composable
fun RootNavigationGraph(navController: NavHostController, scaffoldState: FGScaffoldState) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = RootDestinationScreen.Splash.route
    ) {

        //--authenticationNavGraph(navController = navController)

        composable(route = RootDestinationScreen.Home.route) {
            HomeScreen(scaffoldState = scaffoldState)
        }

        composable(route = RootDestinationScreen.Splash.route) {
            SplashScreen(
                onAnimationFinish = {
                    navController.popBackStack()
                    navController.navigate(RootDestinationScreen.Home.route)

                }
            )
        }
    }

}

sealed class RootDestinationScreen(val route: String) {
    object Home : RootDestinationScreen(route = "HOME")
    object Splash : RootDestinationScreen(route = "SPLASH")
}