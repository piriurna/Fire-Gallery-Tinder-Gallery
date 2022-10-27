package com.artemissoftware.firegallery.screens.login

import com.artemissoftware.firegallery.ui.FGBaseEvents

sealed class LogInEvents: FGBaseEvents() {

    data class ValidateLogin(val email: String, val password: String): LogInEvents()
    data class LogIn(val email: String, val password: String): LogInEvents()
}