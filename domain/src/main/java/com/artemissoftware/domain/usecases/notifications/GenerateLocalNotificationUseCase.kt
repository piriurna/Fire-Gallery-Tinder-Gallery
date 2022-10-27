package com.artemissoftware.domain.usecases.notifications

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.LocalNotification
import com.artemissoftware.domain.repositories.LocalNotificationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenerateLocalNotificationUseCase @Inject constructor(private val localNotificationsRepository: LocalNotificationsRepository) {

    operator fun invoke(localNotification: LocalNotification): Flow<Resource<Any>> = flow {

        localNotificationsRepository.generateNotification(localNotification = localNotification)
        emit(Resource.Success())
    }

}