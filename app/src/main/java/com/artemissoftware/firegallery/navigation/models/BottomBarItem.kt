package com.artemissoftware.firegallery.navigation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    title: String,
    activeIcon: ImageVector,
    inactiveIcon: ImageVector,
    route: String
) {
    object Home : BottomBarItem(
        route = "HOME",
        title = "HOME",
        activeIcon = Icons.Default.Place,
        inactiveIcon = Icons.Outlined.Place
    )
}
