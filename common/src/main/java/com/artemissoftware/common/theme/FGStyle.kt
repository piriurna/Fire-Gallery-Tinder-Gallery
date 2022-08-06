package com.artemissoftware.common.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.artemissoftware.common.R

object FGStyle {

    private val MeOneFont = FontFamily(
        Font(R.font.textmeone_regular),
        Font(R.font.textmeone_regular, FontWeight.W500),
        Font(R.font.textmeone_regular, FontWeight.Bold)
    )

    val TextMeOne = TextStyle(
        fontFamily = MeOneFont,
        fontSize = 14.sp
    )

    val TextMeOne12 = TextMeOne.copy(
        fontSize = 12.sp
    )

    val TextMeOneBold = TextMeOne.copy(
        fontWeight = FontWeight.Bold
    )

    val TextMeOneBold12 = TextMeOneBold.copy(
        fontSize = 12.sp
    )

    val TextMeOneBold16 = TextMeOneBold.copy(
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

    val TextOswaldMedium = TextOswald.copy(
        fontWeight = FontWeight.W500
    )

    val TextOswaldBold36 = TextOswaldBold.copy(
        fontSize = 36.sp
    )
}
