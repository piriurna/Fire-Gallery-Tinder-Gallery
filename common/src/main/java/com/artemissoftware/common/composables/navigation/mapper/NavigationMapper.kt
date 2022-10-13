package com.artemissoftware.common.composables.navigation.mapper

import androidx.compose.ui.graphics.vector.ImageVector
import com.artemissoftware.common.composables.navigation.models.BaseDestinations
import com.artemissoftware.common.composables.navigation.models.BottomBarItem

fun BaseDestinations.toBottomBarItem(
    title: String,
    activeIcon: ImageVector,
    inactiveIcon: ImageVector
): BottomBarItem{

    return BottomBarItem(
        route = this.route,
        title = title,
        activeIcon = activeIcon,
        inactiveIcon = inactiveIcon
    )
}