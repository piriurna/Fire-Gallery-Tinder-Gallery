package com.artemissoftware.firegallery.ui

sealed class UIEvent {
    data class ShowDialog(val message: String): UIEvent()
    data class ShowErrorDialog(val title: String, val message: String): UIEvent()
}
