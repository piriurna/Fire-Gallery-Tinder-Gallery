package com.artemissoftware.common.composables.navigation.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(val title: String,
                         val activeIcon: ImageVector,
                         val inactiveIcon: ImageVector,
                         val route: String)
