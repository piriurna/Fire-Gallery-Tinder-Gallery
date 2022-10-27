package com.artemissoftware.common.composables.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.common.theme.primaryText

@Composable
fun FGText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primaryText,
    textAlign: TextAlign? = null,
    style: TextStyle = FGStyle.TextAlbertSans,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {

    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        style = style,
        letterSpacing = letterSpacing,
        overflow = overflow,
        maxLines = maxLines
    )
}

@Preview
@Composable
private fun FGTextPreview() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FGText(
            text = "Fire gallery - AlbertSans",
            style = FGStyle.TextAlbertSans
        )

        FGText(
            text = "Fire gallery - AlbertSans",
            style = FGStyle.TextAlbertSansBold
        )

        FGText(
            text = "Fire gallery - AlbertSans",
            style = FGStyle.TextAlbertSansMedium
        )

        FGText(
            text = "Fire gallery - Oswald",
            style = FGStyle.TextOswald
        )

        FGText(
            text = "Fire gallery - Oswald",
            style = FGStyle.TextOswaldBold,

        )

        FGText(
            text = "Fire gallery - Oswald",
            style = FGStyle.TextOswaldBold36,

            )
    }

}