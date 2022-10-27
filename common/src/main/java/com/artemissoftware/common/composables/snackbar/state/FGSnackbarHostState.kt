package com.artemissoftware.common.composables.snackbar.state

import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.resume

class FGSnackbarHostState {

    private val mutex = Mutex()

    var currentEDPSnackbarData by mutableStateOf<FGSnackbarData?>(null)
        private set

    suspend fun showSnackbar(
//        mode: EDPSnackbarMode,
        message: String,
//        actionLabel: String? = null,
//        duration: SnackbarDuration = SnackbarDuration.Short
    ): SnackbarResult = mutex.withLock {
        try {
            return suspendCancellableCoroutine { continuation ->
                currentEDPSnackbarData = FGSnackbarDataImpl(
//                    mode,
                    message = message,
//                    actionLabel,
//                    duration,
                    continuation = continuation
                )
            }
        } finally {
            currentEDPSnackbarData = null
        }
    }

    @Stable
    private class FGSnackbarDataImpl(
//        override val mode: EDPSnackbarMode,
        override val message: String,
//        override val actionLabel: String?,
//        override val duration: SnackbarDuration,
        private val continuation: CancellableContinuation<SnackbarResult>
    ) : FGSnackbarData {

        override fun performAction() {
            if (continuation.isActive) continuation.resume(SnackbarResult.ActionPerformed)
        }

        override fun dismiss() {
            if (continuation.isActive) continuation.resume(SnackbarResult.Dismissed)
        }
    }
}