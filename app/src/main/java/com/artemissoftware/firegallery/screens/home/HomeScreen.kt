package com.artemissoftware.firegallery.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.firegallery.screens.home.models.HomeTabs


@Composable
fun HomeScreen() {

    FGScaffold(
        bottomBarItems = HomeTabs.TABS,
    ) {
        //HomeNavigationGraph(navController = navController)
    }

}




@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {

    HomeScreen()
}