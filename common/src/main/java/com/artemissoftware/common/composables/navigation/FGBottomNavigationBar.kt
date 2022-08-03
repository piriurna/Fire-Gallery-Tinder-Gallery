package com.artemissoftware.common.composables.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.models.NavigationItem

@Composable
fun FGBottomNavigationBar (
    items: List<NavigationItem>
) {

    var selectedScreen by remember { mutableStateOf(0) }

    Box(
        Modifier
            .shadow(5.dp)
            .background(color = MaterialTheme.colors.surface)
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            for (item in items) {
                val isSelected = item == items[selectedScreen]
                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)

                Box(
                    modifier = Modifier.weight(animatedWeight),
                    contentAlignment = Alignment.Center,
                ) {

                    val interactionSource = remember { MutableInteractionSource() }

                    FGBottomNavigationItem(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            selectedScreen = items.indexOf(item)
                        },
                        item = item,
                        isSelected = isSelected
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun FGBottomNavigationBarPreview() {

    FGBottomNavigationBar(items = listOf(MockNavigationBar.Create, MockNavigationBar.Profile))
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