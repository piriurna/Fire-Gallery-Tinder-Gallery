package com.artemissoftware.firegallery.screens.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.usecases.profile.ValidateRegisterUseCase
import com.artemissoftware.firegallery.screens.pictures.PictureState
import com.artemissoftware.firegallery.screens.profile.ProfileEvents
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateRegisterUseCase: ValidateRegisterUseCase
): FGBaseEventViewModel<RegisterEvents>() {

    private val _state: MutableState<RegisterState> = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state


    override fun onTriggerEvent(event: RegisterEvents) {
        when(event){

            is RegisterEvents.ValidateRegister ->{
                validateRegister(email = event.email, password = event.password, passwordConfirm = event.passwordConfirm, username = event.username)
            }

            is RegisterEvents.Register ->{
                registerUser(email = event.email, password = event.password, username = event.username)
            }
        }
    }


    private fun validateRegister(email: String, password: String, passwordConfirm: String, username: String){

        validateRegisterUseCase.invoke(email = email, password = password, passwordConfirm = passwordConfirm, username = username).onEach { result ->

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


    private fun registerUser(email: String, password: String, username: String){

    }
}