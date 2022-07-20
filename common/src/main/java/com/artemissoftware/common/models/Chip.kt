package com.artemissoftware.common.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


class Chip(
    val name: String,
    enabled: Boolean = true
) {
    val enabled = mutableStateOf(enabled)

    companion object{

        val mockChips = listOf(
            Chip("Apple", false),
            Chip("Bananas"),
            Chip("Cherries")
        )
    }
}
