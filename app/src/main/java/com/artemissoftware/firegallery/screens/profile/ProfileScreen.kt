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
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.firegallery.screens.picturedetail.PictureDetailState
import com.artemissoftware.firegallery.screens.profile.composables.ProfileOption

@Composable
fun ProfileScreen() {

    val viewModel: ProfileViewModel = hiltViewModel()
    val state = viewModel.state.value

    BuildProfileScreen(state = state)


}

@Composable
private fun BuildProfileScreen(state: ProfileState) {

    FGScaffold(
        isLoading = state.isLoading
    ) {

        Column {

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
                        onCheck = {}
                    )

                }

            }
        }


    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    BuildProfileScreen(ProfileState())
}
