package com.artemissoftware.firegallery.screens.favorites

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.GetFavoritePicturesUseCase
import com.artemissoftware.domain.usecases.UpdateFavoriteUseCase
import com.artemissoftware.firegallery.screens.pictures.PictureState
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritePicturesUseCase: GetFavoritePicturesUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
): FGBaseEventViewModel<FavoriteEvents>() {

    private val _state: MutableState<PictureState> = mutableStateOf(PictureState())
    val state: State<PictureState> = _state

    init {
        onTriggerEvent(FavoriteEvents.GetFavorites)
    }

    override fun onTriggerEvent(event: FavoriteEvents) {
        when(event){

            is FavoriteEvents.GetFavorites -> {
                getFavorites()
            }
            is FavoriteEvents.Remove -> {
                remove(event.pictureId)
            }
        }
    }

    private fun remove(pictureId: String) {
        updateFavoriteUseCase.invoke(pictureId = pictureId, isFavorite = false).onEach { result ->

            val favorites = _state.value.pictures.toMutableList()
            favorites.removeIf { it.id == pictureId }

            _state.value = _state.value.copy(
                pictures = favorites,
                isLoading = false
            )

        }.launchIn(viewModelScope)
    }


    private fun getFavorites(){

        getFavoritePicturesUseCase.invoke().onEach { result ->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        pictures = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
//                is Resource.Error -> {
//
//                    _state.value = state.value.copy(
//                        pictures = result.data ?: emptyList(),
//                        isLoading = false
//                    )
//                    _eventFlow.emit(UIEvent.ShowSnackbar(
//                        result.message ?: "Unknown error"
//                    ))
//                }
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
