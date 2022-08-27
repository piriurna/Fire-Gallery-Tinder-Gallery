package com.artemissoftware.firegallery.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.firegallery.screens.picturedetail.PictureDetailState
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
    scaffoldState: FGScaffoldState,
    state: ProfileState,
    events: ((ProfileEvents) -> Unit),
) {

    FGScaffold(
        isLoading = state.isLoading
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(text = "Profile",
                style = FGStyle.TextMeOneBold32,
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
                        isChecked = state.profile.notifications,
                        description = "Allow app to receive push notifications",
                        onCheck = {
                            val profile = state.profile
                            profile.notifications = it
                            events.invoke(ProfileEvents.UpdateProfile(profile))

                            scaffoldState.showSuccess("translateResource(R.string.access_email_to_recover_password, email)", "translateResource(R.string.ok)")

                        }
                    )

                }

            }
        }


    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    BuildProfileScreen(
        scaffoldState =FGScaffoldState(),
        state = ProfileState(),
        events = {}
    )
}
