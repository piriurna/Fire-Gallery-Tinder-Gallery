package com.artemissoftware.firegallery.navigation.navtypes

import android.os.Bundle
import androidx.navigation.NavType
import com.artemissoftware.firegallery.screens.gallery.models.GalleryUI
import com.google.gson.Gson


class GalleryUINavType : NavType<GalleryUI>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): GalleryUI? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): GalleryUI {
        return Gson().fromJson(value, GalleryUI::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: GalleryUI) {
        bundle.putParcelable(key, value)
    }
}