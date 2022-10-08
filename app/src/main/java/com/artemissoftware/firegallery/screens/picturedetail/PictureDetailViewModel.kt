package com.artemissoftware.firegallery.screens.picturedetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.usecases.GetPictureDetailUseCase
import com.artemissoftware.domain.usecases.favorite.UpdateFavoriteUseCase
import com.artemissoftware.firegallery.navigation.NavigationArguments
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PictureDetailViewModel @Inject constructor(
    private val getPictureDetailUseCase: GetPictureDetailUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    savedStateHandle: SavedStateHandle
): FGBaseEventViewModel<PictureDetailEvents>(){

    private val _state: MutableState<PictureDetailState> = mutableStateOf(PictureDetailState())
    val state: State<PictureDetailState> = _state

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite


    init {
        val pictureId = savedStateHandle.get<String>(NavigationArguments.PICTURE_ID).orEmpty()
        onTriggerEvent(PictureDetailEvents.GetPicture(pictureId))
    }

    override fun onTriggerEvent(event: PictureDetailEvents) {

        when(event){
            is PictureDetailEvents.GetPicture -> {
                getPicture(event.id)
            }

            is PictureDetailEvents.FavoritePicture -> {
                saveFavorite(pictureId = event.id, isFavorite = event.isFavorite)
            }
        }
    }

    private fun saveFavorite(pictureId: String, isFavorite: Boolean) {

        _isFavorite.value = isFavorite

        updateFavoriteUseCase(pictureId = pictureId, isFavorite = isFavorite)
            .onEach {

        }.launchIn(viewModelScope)
    }


    private fun getPicture(pictureId: String){

        getPictureDetailUseCase(pictureId).onEach { result->

            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        picture = result.data,
                        isLoading = false
                    )
                    result.data?.let {
                        _isFavorite.value = it.isFavorite
                    }

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