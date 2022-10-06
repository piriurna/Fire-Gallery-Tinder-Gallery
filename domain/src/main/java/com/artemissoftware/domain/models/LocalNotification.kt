package com.artemissoftware.domain.models

data class LocalNotification(
//    val group: LocalNotificationGroup,
//    val channel: LocalNotificationChannel,
    val title: String? = null,
    val text: String? = null,
    val link: String? = null,
    val cls: Class<*>? = null,
)
