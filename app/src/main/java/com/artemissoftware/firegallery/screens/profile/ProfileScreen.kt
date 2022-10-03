package com.artemissoftware.firegallery.screens.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.theme.InfoBlue
import com.artemissoftware.firegallery.screens.profile.composables.ProfileOption


@Composable
fun ProfileScreen(scaffoldState: FGScaffoldState) {

    val viewModel: ProfileViewModel = hiltViewModel()
    val state = viewModel.state.value

    BuildProfileScreen(
        scaffoldState = scaffoldState,
        state = state,
        events = viewModel::onTriggerEvent
    )
}

@Composable
private fun BuildProfileScreen(
    scaffoldState: FGScaffoldState? = null,
    state: ProfileState,
    events: ((ProfileEvents) -> Unit),
) {

    val context = LocalContext.current

    FGScaffold(
        isLoading = state.isLoading
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            FGText(
                text = "Profile"
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {

                    ProfileOption(
                        icon = Icons.Filled.Notifications,
                        iconColor = InfoBlue,
                        isChecked = state.profile.notifications,
                        description = "Allow app to receive push notifications",
                        onCheck = {

                            events.invoke(ProfileEvents.UpdateProfile(notificationsEnabled = it))
                        }
                    )

                }

                item {
                    ProfileOption(
                        icon = Icons.Filled.AccountBox,
                        iconColor = InfoBlue,
                        title = "Firebase Token",
                        description = state.profile.firebaseToken,
                        onClick = {

                            val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("token", state.profile.firebaseToken)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Token copiado", Toast.LENGTH_SHORT).show()
                        }

                    )
                }

            }
        }


    }
}
