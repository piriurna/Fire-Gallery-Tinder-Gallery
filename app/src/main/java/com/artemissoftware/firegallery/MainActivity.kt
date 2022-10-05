package com.artemissoftware.firegallery

import android.Manifest
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.artemissoftware.domain.models.LocalNotification
import com.artemissoftware.firegallery.navigation.MY_ARG
import com.artemissoftware.firegallery.navigation.MY_LOLO
import com.artemissoftware.firegallery.navigation.MY_URI
import com.artemissoftware.firegallery.navigation.RootNavigationGraph
import com.artemissoftware.firegallery.ui.theme.FireGalleryTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    @Inject
    internal lateinit var notificationBuilder: NotificationCompat.Builder
    @Inject
    internal lateinit var notificationManager: NotificationManagerCompat


    private fun lolo(){
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0
        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            "$MY_URI/$MY_ARG=Coming from Notification&$MY_LOLO=lopes".toUri(),
            //"$MY_URI/$MY_ARG=Coming from Notification&".toUri(),
            this,
            MainActivity::class.java
        )
        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, flag)
        }

        val localNotification = LocalNotification(title = "Title notification", text = "text notification", )

        val notif = notificationBuilder
            .setContentIntent(clickPendingIntent)
            .setContentTitle(localNotification.title)
            .build()

        notificationManager.notify(1, notif
        )
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
