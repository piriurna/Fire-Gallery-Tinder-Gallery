package com.artemissoftware.firegallery

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.artemissoftware.domain.models.LocalNotification
import com.artemissoftware.domain.repositories.LocalNotificationsRepository
import com.artemissoftware.firegallery.navigation.NavigationArguments
import com.artemissoftware.firegallery.navigation.RootNavigationGraph
import com.artemissoftware.firegallery.ui.theme.FireGalleryTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    @Inject
    lateinit var localNotificationsRepository: LocalNotificationsRepository


    private fun lolo(){
//        val flag =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//                PendingIntent.FLAG_IMMUTABLE
//            else
//                0
//        val clickIntent = Intent(
//            Intent.ACTION_VIEW,
//            "${NavigationArguments.ARTEMIS_SOFTWARE_URI}/${NavigationArguments.PICTURE_ID}=AABB".toUri(),
//            //"$MY_URI/$MY_ARG=Coming from Notification&".toUri(),
//            this,
//            MainActivity::class.java
//        )
//        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack(clickIntent)
//            getPendingIntent(1, flag)
//        }
//
//        val localNotification = LocalNotification(title = "Title notification", text = "text notification", )
//
//        val notif = notificationBuilder
//            .setContentIntent(clickPendingIntent)
//            .setContentTitle(localNotification.title)
//            .build()
//
//        notificationManager.notify(1, notif
//        )

        val localNotification = LocalNotification(title = "New Image", text = "Check artemis by artemisSoftware", link = "https://artemis-software.com/pictureId=AABB", cls = MainActivity::class.java)
        localNotificationsRepository.generateNotification(localNotification)

    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootNavigationGraph(navController = rememberAnimatedNavController(), viewModel.scaffoldState)
                }
            }
        }

        lolo()
    }

}
