package com.artemissoftware.common.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FGCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    elevation: Dp = 4.dp,
    shape: Shape = MaterialTheme.shapes.medium,
    content: @Composable () -> Unit
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        elevation = elevation,
        shape = shape,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun FGCardPreview() {

    FGCard{
        Text(text = "Example")
    }
    
}