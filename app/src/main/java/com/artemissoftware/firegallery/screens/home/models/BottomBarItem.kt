package com.artemissoftware.firegallery.screens.home.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.artemissoftware.common.models.NavigationItem

sealed class BottomBarItem(
    title: String,
    activeIcon: ImageVector,
    inactiveIcon: ImageVector,
    route: String
) : NavigationItem(title = title, activeIcon = activeIcon, inactiveIcon = inactiveIcon, route = route){

    object Gallery : BottomBarItem(
        route = "GALLERY",
        title = "Gallery",
        activeIcon = Icons.Default.Place,
        inactiveIcon = Icons.Outlined.Place
    )
}
