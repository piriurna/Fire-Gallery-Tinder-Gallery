package com.artemissoftware.firegallery.screens.favorites

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.GetPicturesUseCase
import com.artemissoftware.firegallery.screens.gallery.GalleryState
import com.artemissoftware.firegallery.screens.pictures.PictureState
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getPicturesUseCase: GetPicturesUseCase
): FGBaseEventViewModel<FavoriteEvents>() {

    private val _state: MutableState<PictureState> = mutableStateOf(PictureState())
    val state: State<PictureState> = _state


    override fun onTriggerEvent(event: FavoriteEvents) {
        when(event){

            is FavoriteEvents.GetFavorites -> {
                getFavorites()
            }

        }
    }


    private fun getFavorites(){

//        getPicturesUseCase.invoke(galleryId = galleryId).onEach { result ->
//
//            when(result) {
//                is Resource.Success -> {
//
//                    _state.value = _state.value.copy(
//                        pictures = result.data ?: emptyList(),
//                        isLoading = false
//                    )
//                }
////                is Resource.Error -> {
////
////                    _state.value = state.value.copy(
////                        pictures = result.data ?: emptyList(),
////                        isLoading = false
////                    )
////                    _eventFlow.emit(UIEvent.ShowSnackbar(
////                        result.message ?: "Unknown error"
////                    ))
////                }
//                is Resource.Loading -> {
//                    _state.value = _state.value.copy(
//                        //pictures = result.data ?: emptyList(),
//                        isLoading = true
//                    )
//                }
//            }
//
//        }.launchIn(viewModelScope)

    }

}
