package com.artemissoftware.firegallery.screens.profile.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.composables.icon.FGCircularIcon
import com.artemissoftware.common.theme.FGStyle

@Composable
fun ProfileOption(
    icon: ImageVector,
    iconColor: Color = Color.Green,
    iconBackgroundColor: Color = Color.Blue,
    description: String,
    isChecked: Boolean = false,
    onCheck: (Boolean) -> Unit
) {

    val checkedState = remember { mutableStateOf(isChecked) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {

        Box(modifier =Modifier
            .weight(0.1F),
        ){

            FGCircularIcon(
                icon = icon,
                iconColor = iconColor,
                iconBackgroundColor = iconBackgroundColor
            )

        }

        Text(text = description,
            style = FGStyle.TextMeOne,
            modifier = Modifier
                .weight(0.8F)
                .padding(horizontal = 12.dp)
        )


        Switch(
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Yellow,
                checkedTrackColor = Color.Black,
                uncheckedThumbColor = Color.DarkGray,
                uncheckedTrackColor = Color.Green,
            ),
            modifier = Modifier.weight(0.1F),
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onCheck.invoke(checkedState.value)
            }
        )

    }
}


@Preview
@Composable
fun ProfileOptionPreview() {

    Column {
        ProfileOption(icon = Icons.Filled.LocationOn, description = "description", onCheck = {})
        ProfileOption(icon = Icons.Filled.LocationOn, description = "descriptiondescriptiondescriptiondescription", onCheck = {})
    }

}