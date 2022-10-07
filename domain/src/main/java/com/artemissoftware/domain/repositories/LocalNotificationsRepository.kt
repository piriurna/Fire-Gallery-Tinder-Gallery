package com.artemissoftware.domain.repositories

import com.artemissoftware.domain.models.LocalNotification

interface LocalNotificationsRepository {

    suspend fun generateNotification(localNotification: LocalNotification)
}