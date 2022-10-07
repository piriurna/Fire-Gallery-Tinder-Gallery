package com.artemissoftware.firegallery.services

import android.util.Log
import com.artemissoftware.domain.models.LocalNotification
import com.artemissoftware.domain.usecases.UpdateFirebaseTokenUseCase
import com.artemissoftware.domain.usecases.notifications.GenerateLocalNotificationUseCase
import com.artemissoftware.firegallery.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class FGFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    internal lateinit var updateFirebaseTokenUseCase: UpdateFirebaseTokenUseCase

    @Inject
    internal lateinit var generateLocalNotificationUseCase: GenerateLocalNotificationUseCase

    private val job = SupervisorJob()

    override fun onNewToken(token: String) {
        saveToken(token = token)
        super.onNewToken(token)
    }


    private fun saveToken(token: String){
        updateFirebaseTokenUseCase.invoke(token).onEach {}.launchIn(
            CoroutineScope(job)
        )
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val localNotification = getLocalNotification(remoteMessage.data)
        sendNotification(localNotification)
    }


    private fun getLocalNotification(data: MutableMap<String, String>): LocalNotification{

        if (data.isNotEmpty()) {
            with(data){
                return LocalNotification(title = get(TITLE), text = get(BODY), link = get(LINK), cls = MainActivity::class.java)
            }
        }

        return LocalNotification()
    }

    private fun sendNotification(localNotification: LocalNotification) {
        generateLocalNotificationUseCase.invoke(localNotification).onEach {}.launchIn(
            CoroutineScope(job)
        )
    }



    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    companion object{
        private const val LINK = "link"
        private const val TITLE = "title"
        private const val BODY = "body"
    }
}