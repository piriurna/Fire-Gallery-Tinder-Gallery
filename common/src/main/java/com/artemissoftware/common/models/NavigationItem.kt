package com.artemissoftware.common.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector
) {
    object Home: NavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Create: NavigationItem("Create", Icons.Filled.Create, Icons.Outlined.Create)
    object Profile: NavigationItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    object Settings: NavigationItem("Settings", Icons.Filled.Settings, Icons.Outlined.Settings)
}
