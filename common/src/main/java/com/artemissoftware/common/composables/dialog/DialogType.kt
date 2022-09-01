package com.artemissoftware.common.composables.dialog

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.artemissoftware.common.theme.SuccessGreen

sealed class DialogType(
    val title: String,
    val description: String,
    val iconColor: Color,
    val mainColor: Color,
    @DrawableRes val imageId: Int? = null,
    val icon: ImageVector? = null,
){
    class Success(
        title: String,
        description: String,
        @DrawableRes imageId: Int? = null,
        icon: ImageVector? = null) : DialogType(title = title, description = description, mainColor = SuccessGreen, iconColor = SuccessGreen, imageId = imageId, icon = icon)
}
