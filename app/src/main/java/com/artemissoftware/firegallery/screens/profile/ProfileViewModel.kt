package com.artemissoftware.firegallery.screens.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.usecases.GetGalleriesUseCase
import com.artemissoftware.domain.usecases.GetProfileUseCase
import com.artemissoftware.domain.usecases.UpdateProfileUseCase
import com.artemissoftware.firegallery.screens.gallery.GalleryEvents
import com.artemissoftware.firegallery.screens.pictures.PictureState
import com.artemissoftware.firegallery.screens.profile.ProfileEvents.*
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase
): FGBaseEventViewModel<ProfileEvents>() {

    private val _state: MutableState<ProfileState> = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

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
        }
    }

    private fun getGetProfile(){

        getProfileUseCase.invoke().onEach { result ->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        profile = result.data ?: Profile(),
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }

        }.launchIn(viewModelScope)


    }


    private fun updateProfile(notificationsEnabled: Boolean){

        val profile = Profile(notifications = notificationsEnabled)

        updateProfileUseCase.invoke(profile).onEach { result ->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        profile = profile
                    )
                }
            }

        }.launchIn(viewModelScope)


    }
}

