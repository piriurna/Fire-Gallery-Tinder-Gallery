package com.artemissoftware.common.composables.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.theme.Orange

@Composable
fun FGButton(
    text: String,
    onClick: () -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange
        ),
        content = {
            FGText(text = text)
        }
    )

}

@Preview(showBackground = true)
@Composable
private fun FGButtonPreview() {

    Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
        FGButton(text = "First option", onClick = {})
        FGButton(text = "Second option", onClick = {})
    }

}
