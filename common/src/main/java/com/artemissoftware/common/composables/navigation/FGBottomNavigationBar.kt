package com.artemissoftware.common.composables.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.artemissoftware.common.models.NavigationItem
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt

@Composable
fun FGBottomNavigationBar (
    items: List<NavigationItem>,
    navController: NavHostController? = null,
    modifier: Modifier = Modifier,
) {

    navController?.let {
        if (items.isNotEmpty()) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            var selectedScreen = remember { mutableStateOf(0) }

            if (showBottomBar(currentDestination, items = items)) {

                FGNavigationBar(
                    modifier = modifier,
                    items = items,
                    navController = navController,
                    selectedScreen = selectedScreen
                )

            }
        }
    }
}


@Composable
private fun FGNavigationBar (
    modifier: Modifier = Modifier,
    items: List<NavigationItem>,
    navController: NavHostController,
    selectedScreen: MutableState<Int>
) {

    Box(
        modifier = modifier
            .shadow(5.dp)
            .background(color = MaterialTheme.colors.surface)
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            for (item in items) {
                val isSelected = item == items[selectedScreen.value]
                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)

                Box(
                    modifier = Modifier.weight(animatedWeight),
                    contentAlignment = Alignment.Center,
                ) {

                    FGBottomNavigationItem(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            selectedScreen.value = items.indexOf(item)

                            navController.navigate(item.route) {

                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        item = item,
                        isSelected = isSelected
                    )
                }
            }
        }
    }
}



private fun showBottomBar(
    currentDestination: NavDestination?,
    items: List<NavigationItem>) = items.any { it.route == currentDestination?.route }


@Preview(showBackground = false)
@Composable
private fun FGNavigationBarPreview() {

    var selectedScreen = remember { mutableStateOf(0) }

    FGNavigationBar(
        items = listOf(MockNavigationBar.Create, MockNavigationBar.Profile),
        navController = rememberNavController(),
        selectedScreen = selectedScreen
    )
}

@Preview(showBackground = false)
@Composable
private fun FGBottomNavigationBarPreview() {

    FGBottomNavigationBar(
        items = listOf(MockNavigationBar.Create, MockNavigationBar.Profile),
        rememberNavController()
    )
}

private sealed class MockNavigationBar(
    title: String,
    activeIcon: ImageVector,
    inactiveIcon: ImageVector,
    route: String,
) : NavigationItem(title, activeIcon, inactiveIcon, route) {
    object Create: MockNavigationBar("Create", Icons.Filled.Create, Icons.Outlined.Create, "Create")
    object Profile: MockNavigationBar("Profile", Icons.Filled.Person, Icons.Outlined.Person, "Profile")
}