package com.artemissoftware.firegallery.screens.home.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Person
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

    object Favorites : BottomBarItem(
        route = "FAVORITES",
        title = "Favorites",
        activeIcon = Icons.Default.Favorite,
        inactiveIcon = Icons.Outlined.Favorite
    )

    object Profile : BottomBarItem(
        route = "PROFILE",
        title = "Profile",
        activeIcon = Icons.Default.Person,
        inactiveIcon = Icons.Outlined.Person
    )
}
