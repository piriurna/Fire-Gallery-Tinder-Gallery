package com.artemissoftware.common.composables.snackbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.theme.FGStyle.TextMeOne

@Composable
fun FGSnackBar(
    icon: ImageVector? = null,
    description: String
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            Text(
                text = description,
                style = TextMeOne
            )
        }
    }
}

@Preview
@Composable
private fun FGSnackBarPreview() {

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {

        FGSnackBar(
            icon = Icons.Default.Notifications,
            description = "snackbarData.message"
        )

        FGSnackBar(
            description = "snackbarData.message.message.message.message.message"
        )

        FGSnackBar(
            icon = Icons.Default.Notifications,
            description = "snackbarData.message.message.message.message.message.message.message.message.message.message.message.message.message"
        )

        FGSnackBar(
            description = "snackbarData.message.message.message.message.message.message.message.message"
        )
    }
}

