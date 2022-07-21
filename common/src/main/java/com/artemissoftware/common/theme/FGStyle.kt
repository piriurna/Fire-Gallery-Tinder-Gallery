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

    private val Oswald = FontFamily(
        Font(R.font.oswald_regular),
        Font(R.font.oswald_medium, FontWeight.W500),
        Font(R.font.oswald_bold, FontWeight.Bold)
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


    val TextOswald = TextStyle(
        fontFamily = Oswald,
        fontSize = 14.sp
    )
}
