package com.artemissoftware.firegallery.screens.pictures

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.GetPicturesUseCase
import com.artemissoftware.domain.usecases.GetPicturesUseCase.Companion.NO_PICTURES_AVAILABLE
import com.artemissoftware.firegallery.navigation.NavigationArguments
import com.artemissoftware.firegallery.screens.gallery.GalleryState
import com.artemissoftware.firegallery.screens.picturedetail.PictureDetailEvents
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import com.artemissoftware.firegallery.ui.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getPicturesUseCase: GetPicturesUseCase,
    savedStateHandle: SavedStateHandle
): FGBaseEventViewModel<PictureEvents>() {

    private val _state: MutableState<PictureState> = mutableStateOf(PictureState())
    val state: State<PictureState> = _state

    init {
        val galleryId = savedStateHandle.get<String>(NavigationArguments.GALLERY_ID).orEmpty()
        onTriggerEvent(PictureEvents.GetPictures(galleryId = galleryId.toInt()))
    }

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
                        isLoading = false,
                        showOptions = true
                    )
                }
                is Resource.Error -> {

                    _state.value = state.value.copy(
                        pictures = result.data ?: emptyList(),
                        isLoading = false,
                        showOptions = false
                    )

                    result.message?.let { showDialog(it) }
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


    private suspend fun showDialog(message: String){

        when(message){

            NO_PICTURES_AVAILABLE ->{

                _eventFlow.emit(
                    UIEvent.ShowInfoDialog(
                        title = "Pictures",
                        message = message
                    )
                )
            }
        }
    }

}
