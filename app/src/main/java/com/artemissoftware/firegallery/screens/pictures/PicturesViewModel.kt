package com.artemissoftware.firegallery.screens.pictures

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.GetPicturesUseCase
import com.artemissoftware.firegallery.screens.gallery.GalleryState
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getPicturesUseCase: GetPicturesUseCase
): FGBaseEventViewModel<PictureEvents>() {

    private val _state: MutableState<PictureState> = mutableStateOf(PictureState())
    val state: State<PictureState> = _state

    override fun onTriggerEvent(event: PictureEvents) {
        when(event){

            is PictureEvents.GetPictures -> {
                getPictures(event.galleryId)
            }

        }
    }


    private fun getPictures(galleryId : Int){

        getPicturesUseCase.invoke(galleryId = galleryId).onEach { result ->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        pictures = result.data ?: emptyList(),
                        isLoading = false
                    )
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
                    _state.value = _state.value.copy(
                        //pictures = result.data ?: emptyList(),
                        isLoading = true
                    )
                }
            }

        }.launchIn(viewModelScope)

    }

}