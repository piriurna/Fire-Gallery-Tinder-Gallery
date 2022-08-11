package com.artemissoftware.data.local.util

import android.content.Context
import androidx.datastore.dataStore
import com.artemissoftware.data.local.util.serializer.ProfileSerializer

val Context.profileStore by dataStore(
    fileName = "app-settings.json",
    serializer = ProfileSerializer
)

