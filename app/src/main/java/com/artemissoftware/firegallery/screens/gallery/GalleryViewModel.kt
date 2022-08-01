package com.artemissoftware.firegallery.screens.gallery

import androidx.lifecycle.viewModelScope
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

        }.launchIn(viewModelScope)


    }
}