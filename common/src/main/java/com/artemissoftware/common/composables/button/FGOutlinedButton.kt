package com.artemissoftware.common.composables.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.theme.Orange


@Composable
fun FGOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    borderColor: Color = Orange,
    enabled: Boolean = true
) {

    val colorBorder = if(enabled) borderColor else borderColor.copy(alpha = 0.2F)

    OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        border = BorderStroke((0.8).dp, colorBorder),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.Transparent
        ),
        content = {
            FGText(
                text = text,
                color = colorBorder
            )
        }

    )
}

@Preview(showBackground = true)
@Composable
private fun FGOutlinedButtonPreview() {

    Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
        FGOutlinedButton(text = "First option", onClick = {})
        FGOutlinedButton(text = "Second option", onClick = {}, enabled = false)

        FGOutlinedButton(text = "First option", onClick = {}, borderColor =Color.Green)
    }

}