package com.artemissoftware.firegallery.screens.picturedetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.GetPictureDetailUseCase
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PictureDetailViewModel @Inject constructor(
    private val getPictureDetailUseCase: GetPictureDetailUseCase
): FGBaseEventViewModel<PictureDetailEvents>(){

    private val _state: MutableState<PictureDetailState> = mutableStateOf(PictureDetailState())
    val state: State<PictureDetailState> = _state

    override fun onTriggerEvent(event: PictureDetailEvents) {

        when(event){
            is PictureDetailEvents.GetPicture -> {
                getPicture(event.id)
            }
        }
    }


    private fun getPicture(pictureId: String){

        getPictureDetailUseCase(pictureId).onEach { result->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        picture = result.data,
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
                        //tickets = result.data ?: emptyList(),
                        isLoading = true
                    )
                }
            }


        }.launchIn(viewModelScope)
    }
}