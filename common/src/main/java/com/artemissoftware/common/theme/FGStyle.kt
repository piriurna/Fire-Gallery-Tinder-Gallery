package com.artemissoftware.common.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.artemissoftware.common.R

object FGStyle {

    private val Oswald = FontFamily(
        Font(R.font.oswald_regular),
        Font(R.font.oswald_medium, FontWeight.W500),
        Font(R.font.oswald_bold, FontWeight.Bold)
    )


    val Text = TextStyle(
        fontFamily = Oswald,
        fontSize = 14.sp
    )
}
