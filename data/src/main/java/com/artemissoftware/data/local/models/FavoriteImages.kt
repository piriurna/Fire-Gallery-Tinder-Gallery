package com.artemissoftware.data.local.models

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteImages(val data: HashMap<String, List<String>> = hashMapOf())
