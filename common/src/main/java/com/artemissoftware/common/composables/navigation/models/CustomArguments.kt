package com.artemissoftware.common.composables.navigation.models

import androidx.navigation.NavType

data class CustomArguments(
    val key: String,
    val type: NavType<*> = NavType.StringType,
    val nullable: Boolean = true
)