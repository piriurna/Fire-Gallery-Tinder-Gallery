package com.artemissoftware.firegallery.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.profile.LogInUseCase
import com.artemissoftware.domain.usecases.profile.ValidateLoginUseCase
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import com.artemissoftware.firegallery.ui.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val loginUseCase: LogInUseCase
) : FGBaseEventViewModel<LogInEvents>() {

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
        validateLoginUseCase.invoke(email = email, password = password).onEach { result ->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        isValidData = result.data?.isValid ?: false
                    )
                }
                else ->{}
            }

        }.launchIn(viewModelScope)
    }

    private fun logInUser(email: String, password: String) {
        loginUseCase.invoke(email = email, password = password).onEach { result ->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        loggedIn = true,
                        isLoading = false
                    )
                }
                is Resource.Error -> {

                    _state.value = _state.value.copy(
                        isLoading = false
                    )

                    _eventFlow.emit(
                        UIEvent.ShowErrorDialog(
                            title = "Authentication",
                            message = result.message ?: "Unknown error"
                        )
                    )
                }
                is Resource.Loading -> {

                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
                else ->{}
            }


        }.launchIn(viewModelScope)
    }
}