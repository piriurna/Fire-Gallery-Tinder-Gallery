package com.artemissoftware.firegallery

import androidx.lifecycle.viewModelScope
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.screens.gallery.GalleryEvents
import com.artemissoftware.firegallery.ui.FGBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): FGBaseEventViewModel<MainEvents>() {

    val scaffoldState by lazy { FGScaffoldState(viewModelScope) }



}