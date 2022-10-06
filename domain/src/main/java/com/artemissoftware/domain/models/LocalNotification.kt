package com.artemissoftware.domain.models

data class LocalNotification(
    val title: String? = null,
    val text: String? = null,
    var link: String? = null,
    var cls: Class<*>? = null,
)
