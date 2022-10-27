package com.artemissoftware.data.local.util

import android.content.Context
import androidx.datastore.dataStore
import com.artemissoftware.data.local.util.serializer.AppSettingsSerializer
import com.artemissoftware.data.local.util.serializer.FavoriteImagesSerializer

val Context.appSettingsStore by dataStore(
    fileName = "app-settings.json",
    serializer = AppSettingsSerializer
)

val Context.profileStore by dataStore(
    fileName = "profile-settings.json",
    serializer = FavoriteImagesSerializer
)
