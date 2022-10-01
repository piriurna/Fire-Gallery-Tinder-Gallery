package com.artemissoftware.firegallery.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.artemissoftware.common.composables.navigation.animatedComposable
import com.artemissoftware.common.composables.navigation.models.BaseDestinations
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.navigation.models.Graph
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
        route = Graph.ROOT,
        startDestination = RootDestinations.Splash.route
    ) {

        //--authenticationNavGraph(navController = navController)

        animatedComposable(route = RootDestinations.Splash.route) {
            SplashScreen(
                scaffoldState = scaffoldState,
                onAnimationFinish = {
                    navController.popBackStack()
                    navController.navigate(RootDestinations.Home.route)

                }
            )
        }

        animatedComposable(route = RootDestinations.Home.route) {
            HomeScreen(scaffoldState = scaffoldState)
        }
    }

}



sealed class RootDestinations(val route: String) : BaseDestinations(route = route){
    object Home : RootDestinations(route = "HOME")
    object Splash : RootDestinations(route = "SPLASH")
}