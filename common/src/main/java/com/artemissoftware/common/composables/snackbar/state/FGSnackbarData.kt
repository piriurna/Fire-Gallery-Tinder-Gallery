package com.artemissoftware.common.composables.snackbar.state

import androidx.compose.material.SnackbarDuration

interface FGSnackbarData {

//    val mode: EDPSnackbarMode
    val message: String
//    val actionLabel: String?
//    val duration: SnackbarDuration

    fun performAction()
    fun dismiss()
}