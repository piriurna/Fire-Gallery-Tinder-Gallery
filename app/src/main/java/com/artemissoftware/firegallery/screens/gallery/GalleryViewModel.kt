package com.artemissoftware.firegallery.screens.gallery

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.GetGalleriesUseCase
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getGalleriesUseCase: GetGalleriesUseCase
): FGBaseEventViewModel<GalleryEvents>() {

    private val _state: MutableState<GalleryState> = mutableStateOf(GalleryState())
    val state: State<GalleryState> = _state

    init {
        onTriggerEvent(GalleryEvents.GetGalleries)
    }

    override fun onTriggerEvent(event: GalleryEvents) {
        when(event){

            is GalleryEvents.GetGalleries -> {
                getGetGalleries()
            }

        }
    }

    private fun getGetGalleries(){

        getGalleriesUseCase.invoke().onEach { result ->

            when(result) {
                is Resource.Success -> {
//                    _state.value = _state.value.copy(
//                        tickets = result.data ?: emptyList(),
//                        //isLoading = false
//                    )
                }
                is Resource.Error -> {
//                    _state.value = _state.value.copy(
//                        tickets = result.data ?: emptyList(),
//                        //isLoading = false
//                    )
////                            _eventFlow.emit(UIEvent.ShowSnackbar(
////                                result.message ?: "Unknown error"
////                            ))
                }
                is Resource.Loading -> {
//                    _state.value = _state.value.copy(
//                        tickets = result.data ?: emptyList(),
//                        //isLoading = true
//                    )
                }
            }

        }.launchIn(viewModelScope)


    }
}