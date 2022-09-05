package com.artemissoftware.common.composables.dialog.models

import com.artemissoftware.common.composables.dialog.DialogButtonType

data class DialogOptions(
    val dialogButtonType: DialogButtonType = DialogButtonType.SINGLE_OPTION,
    val confirmationText: String,
    val confirmation: () -> Unit = {},
    val cancelText: String? = null,
    val cancel: () -> Unit = {},
)
