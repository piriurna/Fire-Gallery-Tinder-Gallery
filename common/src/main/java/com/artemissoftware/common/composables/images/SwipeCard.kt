package com.artemissoftware.common.composables.images

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.composables.FGCard
import com.artemissoftware.common.extensions.Swipe
import com.artemissoftware.common.extensions.rememberSwipeState
import com.artemissoftware.common.extensions.swiper
import com.artemissoftware.common.models.SwipeResult

@ExperimentalMaterialApi
@Composable
fun SwipeCard(
    modifier: Modifier = Modifier,
    onSwiped: (result: SwipeResult) -> Unit,
    swipeState : Swipe,
    content: @Composable (BoxScope.() -> Unit)
) {

    FGCard(
        modifier = modifier
            .padding(4.dp)
            .clickable(enabled = false, onClick = {})
            .swiper(
                state = swipeState,
                onDragAccepted = {
                    onSwiped(SwipeResult.ACCEPT)
                },
                onDragRejected = {
                    onSwiped(SwipeResult.REJECT)
                },
            ),
        elevation = 12.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            modifier = Modifier,
            content = content
        )
    }

}

@Preview
@ExperimentalMaterialApi
@Composable
fun PreviewSwipeCard() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        SwipeCard(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(
                    align = Alignment.Center
                ),
            onSwiped = { /*TODO*/ },
            swipeState = rememberSwipeState(maxWidth = constraints.maxWidth.toFloat(), maxHeight = constraints.maxHeight.toFloat())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
            ) {
                Text(

                    text = "Teste"
                )
            }
        }

    }
}