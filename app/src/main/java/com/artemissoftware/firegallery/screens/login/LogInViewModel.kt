package com.artemissoftware.firegallery.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor() : FGBaseEventViewModel<LogInEvents>() {

    private val _state: MutableState<LogInState> = mutableStateOf(LogInState())
    val state: State<LogInState> = _state

    override fun onTriggerEvent(event: LogInEvents) {
        when(event){

            is LogInEvents.ValidateLogin ->{
                validateLogIn(email = event.email, password = event.password)
            }

            is LogInEvents.LogIn ->{
                logInUser(email = event.email, password = event.password)
            }
        }
    }

    private fun validateLogIn(email: String, password: String) {

    }

    private fun logInUser(email: String, password: String) {

    }
}