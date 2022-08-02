package com.artemissoftware.common.composables.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.models.NavigationItem

@Composable
fun FGBottomNavigationItem (
    modifier: Modifier = Modifier,
    item: NavigationItem,
    isSelected: Boolean
) {

    val animatedHeight by animateDpAsState(targetValue = if (isSelected) 36.dp else 26.dp)
    val animatedElevation by animateDpAsState(targetValue = if (isSelected) 15.dp else 0.dp)
    val animatedAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else .5f)
    val animatedIconSize by animateDpAsState(
        targetValue = if (isSelected) 26.dp else 20.dp,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .height(animatedHeight)
                .shadow(
                    elevation = animatedElevation,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(20.dp)
                )
                ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                rememberVectorPainter(
                    image = if (isSelected) item.activeIcon else item.inactiveIcon
                ),
                contentDescription = item.title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight()
                    .padding(start = 12.dp)
                    .alpha(animatedAlpha)
                    .size(animatedIconSize),
            )

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = item.title,
                    modifier = Modifier.padding(start = 8.dp, end = 10.dp),
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun FGBottomNavigationItemPreview() {

    Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {

        FGBottomNavigationItem(item = NavigationItem.Home, isSelected = false)

        FGBottomNavigationItem(item = NavigationItem.Settings, isSelected = true)
    }


}