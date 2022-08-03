package com.artemissoftware.common.models

import androidx.compose.ui.graphics.vector.ImageVector

abstract class NavigationItem (
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
    val route: String,
)