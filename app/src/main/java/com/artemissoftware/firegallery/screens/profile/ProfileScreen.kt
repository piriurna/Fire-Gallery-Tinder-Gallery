package com.artemissoftware.firegallery.screens.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.theme.FGStyle.TextAlbertSansBold28
import com.artemissoftware.common.theme.InfoBlue
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.firegallery.R
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
    events: ((ProfileEvents) -> Unit)? = null,
) {

    val context = LocalContext.current

    FGScaffold(
        isLoading = state.isLoading,
        modifier = Modifier.padding(4.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            FGText(
                modifier = Modifier.padding(top = 12.dp),
                style = TextAlbertSansBold28,
                text = stringResource(R.string.profile)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {

                    ProfileOption(
                        icon = Icons.Filled.Notifications,
                        iconColor = InfoBlue,
                        isChecked = state.profile.notifications,
                        description = stringResource(R.string.allow_receive_push_notifications),
                        onCheck = {

                            events?.invoke(ProfileEvents.UpdateProfile(notificationsEnabled = it))
                        }
                    )

                }

                item {
                    ProfileOption(
                        icon = Icons.Filled.AccountBox,
                        iconColor = InfoBlue,
                        title = stringResource(R.string.firebase_token),
                        description = state.profile.firebaseToken,
                        onClick = {

                            val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("token", state.profile.firebaseToken)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, R.string.token_copied, Toast.LENGTH_SHORT).show()
                        }

                    )
                }

                item {
                    ProfileOption(
                        icon = Icons.Filled.Favorite,
                        iconColor = InfoBlue,
                        title = state.profile.favorites.size.toString(),
                        description = stringResource(R.string.number_favorite_pictures),
                        onClick = {

                            //TODO: Navigate to favorites tab
                        }

                    )
                }

            }
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun BuildProfileScreenPreview() {

    BuildProfileScreen(state = ProfileState(profile = Profile.mockProfile))
}