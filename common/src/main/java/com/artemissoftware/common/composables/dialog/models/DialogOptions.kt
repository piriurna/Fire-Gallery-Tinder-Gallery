package com.artemissoftware.common.composables.dialog.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

data class DialogOptions(
    @StringRes val confirmationTextId: Int,
    val confirmation: () -> Unit = {},
    @StringRes val cancelTextId: Int? = null,
    val cancel: () -> Unit = {},
) {


    fun getOptionsType(): DialogButtonType{

        return when{

            (cancelTextId != null) ->{
                DialogButtonType.DOUBLE_OPTION
            }
            else ->{
                DialogButtonType.SINGLE_OPTION
            }

        }
    }


}
