package com.artemissoftware.common.composables.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType

enum class FGTextFieldType {

    TEXT,
    TEXT_AREA,
    NUMERIC,
    NAME,
    PLATE,
    NICKNAME,
    PHONE,
    EMAIL,
    SEARCH,
    PASSWORD,
    CAMERA_CAPTURE;

    @Composable
    fun getKeyboardType(): KeyboardType = when (this) {
        NUMERIC, PHONE -> KeyboardType.Number
        EMAIL -> KeyboardType.Email
        PASSWORD -> KeyboardType.Password
        else -> KeyboardType.Text
    }

    @Composable
    fun isShowClearButton(): Boolean = when (this) {
        PASSWORD -> false
        else -> true
    }

    @Composable
    fun getRegex(): String? = when (this) {
        NAME -> null
        else -> null
    }

}