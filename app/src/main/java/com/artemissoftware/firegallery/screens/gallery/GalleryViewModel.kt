package com.artemissoftware.firegallery.screens.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.usecases.GetGalleriesUseCase
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class GalleryViewModel @Inject constructor(
    //private val getGalleriesUseCase: GetGalleriesUseCase
): FGBaseEventViewModel<GalleryEvents>() {

    init {
        onTriggerEvent(GalleryEvents.GetGalleries)
    }

    override fun onTriggerEvent(event: GalleryEvents) {
        super.onTriggerEvent(event)
    }
}