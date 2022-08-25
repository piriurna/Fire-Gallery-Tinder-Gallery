package com.artemissoftware.firegallery.screens.profile

import com.artemissoftware.firegallery.screens.pictures.PictureEvents
import com.artemissoftware.firegallery.ui.FGBaseEvents

sealed class ProfileEvents: FGBaseEvents() {
    object GetProfile: ProfileEvents()
    data class UpdateProfile(val notificationsEnabled: Boolean): ProfileEvents()
}
