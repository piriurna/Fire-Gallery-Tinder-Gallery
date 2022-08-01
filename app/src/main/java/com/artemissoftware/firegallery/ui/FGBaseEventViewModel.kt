package com.artemissoftware.firegallery.ui

import androidx.lifecycle.ViewModel


abstract class FGBaseEventViewModel<E: FGBaseEvents> : ViewModel() {
    open fun onTriggerEvent(event: E) {}
}