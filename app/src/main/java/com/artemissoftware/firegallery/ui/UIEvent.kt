package com.artemissoftware.firegallery.ui

sealed class UIEvent {
    data class ShowDialog(val message: String): UIEvent()
}
