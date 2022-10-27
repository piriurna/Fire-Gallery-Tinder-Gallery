package com.artemissoftware.firegallery.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


abstract class FGBaseEventViewModel<E: FGBaseEvents> : ViewModel() {


    protected val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()

    open fun onTriggerEvent(event: E) {}
}