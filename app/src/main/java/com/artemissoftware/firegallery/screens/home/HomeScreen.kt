package com.artemissoftware.firegallery.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.firegallery.navigation.HomeNavigationGraph
import com.artemissoftware.firegallery.screens.home.models.HomeTabs


@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    FGScaffold(
        bottomBarItems = HomeTabs.TABS,
        navController = navController
    ) {
        HomeNavigationGraph(navController = navController)
    }

}




@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {

    HomeScreen()
}