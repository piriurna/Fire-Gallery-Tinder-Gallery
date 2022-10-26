package com.artemissoftware.firegallery.screens.profile

import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.AppConfig
import com.artemissoftware.domain.usecases.profile.GetProfileUseCase
import com.artemissoftware.domain.usecases.profile.LogOutUseCase
import com.artemissoftware.domain.usecases.profile.UpdateProfileUseCase
import com.artemissoftware.firegallery.screens.profile.ProfileEvents.GetProfile
import com.artemissoftware.firegallery.screens.profile.ProfileEvents.UpdateProfile
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val logOutUseCase: LogOutUseCase
): FGBaseEventViewModel<ProfileEvents>() {


    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state


    init {
        getGetProfile()
    }

    override fun onTriggerEvent(event: ProfileEvents) {
        when(event){

            is GetProfile->{
                getGetProfile()
            }
            is UpdateProfile->{
                updateProfile(notificationsEnabled = event.notificationsEnabled)
            }
            is ProfileEvents.LogOut->{
                logOut()
            }
        }
    }

    private fun getGetProfile(){

        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true
            )

            getProfileUseCase.invoke().collectLatest { result ->

                _state.value = _state.value.copy(
                    appConfig = result,
                    user = result.user,
                    isLoading = false
                )
            }
        }
    }

    private fun updateProfile(notificationsEnabled: Boolean){

        val appConfig = AppConfig(notifications = notificationsEnabled)

        updateProfileUseCase.invoke(appConfig).onEach { result ->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        appConfig = appConfig
                    )
                }
                else ->{}
            }

        }.launchIn(viewModelScope)
    }

    private fun logOut(){

        logOutUseCase.invoke().onEach { result ->

            when(result) {
                is Resource.Success -> {
//TODO:remover isto
//                    val profile = _state.value.appConfig
//                    profile.user = null
//                    _state.value = _state.value.copy(
//                        appConfig = profile,
//                        user = null,
//                        isLoading = false
//                    )
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

