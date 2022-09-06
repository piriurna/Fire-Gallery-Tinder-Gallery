package com.artemissoftware.common.composables.dialog.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.artemissoftware.common.composables.dialog.models.DialogOptions
import com.artemissoftware.common.theme.ErrorRed
import com.artemissoftware.common.theme.SuccessGreen

sealed class DialogType(
    val title: String,
    val description: String,
    val iconColor: Color,
    val mainColor: Color,
    @DrawableRes val imageId: Int? = null,
    val icon: ImageVector? = null,
    val dialogOptions: DialogOptions
){
    class Success(
        title: String,
        description: String,
        @DrawableRes imageId: Int? = null,
        icon: ImageVector? = null,
        dialogOptions: DialogOptions
    ) : DialogType(title = title, description = description, mainColor = SuccessGreen, iconColor = SuccessGreen, imageId = imageId, icon = icon, dialogOptions = dialogOptions)

    class Error(
        title: String,
        description: String,
        @DrawableRes imageId: Int? = null,
        icon: ImageVector? = null,
        dialogOptions: DialogOptions
    ) : DialogType(title = title, description = description, mainColor = ErrorRed, iconColor = ErrorRed, imageId = imageId, icon = icon, dialogOptions = dialogOptions)
}
