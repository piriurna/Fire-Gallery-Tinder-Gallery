package com.artemissoftware.common.composables.images

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.composables.FGCard
import com.artemissoftware.common.extensions.rememberSwipeState
import com.artemissoftware.common.extensions.swiper
import com.artemissoftware.common.models.SwipeResult

@ExperimentalMaterialApi
@Composable
fun SwipeCard(
    modifier: Modifier = Modifier,
    onSwiped: (result: SwipeResult) -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    val swiped = remember { mutableStateOf(false) }

    BoxWithConstraints {
        val swipeState = rememberSwipeState(
            maxWidth = constraints.maxWidth.toFloat(),
            maxHeight = constraints.maxHeight.toFloat()
        )

        if (swiped.value.not()) {
            FGCard(
                modifier = modifier.padding(4.dp),
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .swiper(
                            state = swipeState,
                            onDragAccepted = {
                                swiped.value = true
                                onSwiped(SwipeResult.ACCEPT)
                            },
                            onDragRejected = {
                                swiped.value = true
                                onSwiped(SwipeResult.REJECT)
                            }
                        ),
                    content = content
                )
            }
        }
    }

}

@Preview
@ExperimentalMaterialApi
@Composable
fun PreviewSwipeCard() {
    SwipeCard(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center), onSwiped = { /*TODO*/ }) {

    }
}