package com.artemissoftware.firegallery.screens.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.usecases.GetProfileUseCase
import com.artemissoftware.domain.usecases.UpdateProfileUseCase
import com.artemissoftware.domain.usecases.setup.SetupAppUseCase
import com.artemissoftware.firegallery.screens.profile.ProfileEvents
import com.artemissoftware.firegallery.screens.profile.ProfileState
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val setupAppUseCase: SetupAppUseCase
): FGBaseEventViewModel<SplashEvents>() {

//    private val _state: MutableState<ProfileState> = mutableStateOf(ProfileState())
//    val state: State<ProfileState> = _state

    init {
        loadAppSettings()
    }

    override fun onTriggerEvent(event: SplashEvents) {
        when(event){

            is SplashEvents.LoadSplash ->{
                loadAppSettings()
            }
        }
    }

    private fun loadAppSettings(){

        setupAppUseCase.invoke().onEach { result ->

            //TODO: identificar situação de erro e sucesso
            val dd = 0
            val kk = dd + 1

        }.launchIn(viewModelScope)


    }

}