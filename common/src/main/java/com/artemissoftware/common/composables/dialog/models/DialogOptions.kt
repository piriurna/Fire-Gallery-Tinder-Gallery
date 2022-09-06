package com.artemissoftware.common.composables.dialog.models

data class DialogOptions(
    val confirmationText: String,
    val confirmation: () -> Unit = {},
    val cancelText: String? = null,
    val cancel: () -> Unit = {},
){

    fun getOptionsType(): DialogButtonType{

        return when{

            (cancelText != null) ->{
                DialogButtonType.DOUBLE_OPTION
            }
            else ->{
                DialogButtonType.SINGLE_OPTION
            }

        }
    }


}
