package com.artemissoftware.firegallery.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.models.NavigationItem


@Composable
fun HomeScreen() {

    FGScaffold(
        bottomBarItems = listOf(NavigationItem.Home, NavigationItem.Settings),
    ) {
        //HomeNavigationGraph(navController = navController)
    }

}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {


}