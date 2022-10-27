package com.artemissoftware.firegallery.screens.favorites

import com.artemissoftware.firegallery.ui.FGBaseEvents

sealed class FavoriteEvents: FGBaseEvents(){

    object GetFavorites : FavoriteEvents()
    data class Remove(val pictureId: String): FavoriteEvents()
}
