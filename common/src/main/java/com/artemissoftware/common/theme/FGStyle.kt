package com.artemissoftware.common.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.artemissoftware.common.R

object FGStyle {

    private val TextMeOne = FontFamily(
        Font(R.font.textmeone_regular),
        Font(R.font.textmeone_regular, FontWeight.W500),
        Font(R.font.textmeone_regular, FontWeight.Bold)
    )

    val Text = TextStyle(
        fontFamily = TextMeOne,
        fontSize = 14.sp
    )

    val TextBold = Text.copy(
        fontWeight = FontWeight.Bold
    )

    val TextBold12 = TextBold.copy(
        fontSize = 12.sp
    )

    val TextBold16 = TextBold.copy(
        fontSize = 16.sp
    )


    private val Oswald = FontFamily(
        Font(R.font.oswald_regular),
        Font(R.font.oswald_medium, FontWeight.W500),
        Font(R.font.oswald_bold, FontWeight.Bold)
    )

    val TextOswald = TextStyle(
        fontFamily = Oswald,
        fontSize = 14.sp
    )

    val TextOswaldBold = TextOswald.copy(
        fontWeight = FontWeight.Bold
    )

    val TextTextOswaldBold36 = TextOswaldBold.copy(
        fontSize = 36.sp
    )
}
