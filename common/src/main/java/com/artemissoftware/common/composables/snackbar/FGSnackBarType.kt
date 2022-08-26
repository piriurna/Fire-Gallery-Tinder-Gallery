package com.artemissoftware.common.composables.snackbar

import androidx.compose.ui.graphics.Color
import com.artemissoftware.common.theme.*

enum class FGSnackBarType(val backgroundColor: Color, val textColor: Color) {

    SUCCESS(backgroundColor = SuccessGreen, textColor = Color.White),
    ERROR(backgroundColor = ErrorRed, textColor = Color.White),
    ALERT(backgroundColor = AlertYellow, textColor = Color.Black),
    INFO(backgroundColor = InfoBlue, textColor = Color.White)

}