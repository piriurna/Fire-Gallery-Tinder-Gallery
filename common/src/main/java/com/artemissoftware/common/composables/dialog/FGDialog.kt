package com.artemissoftware.common.composables.dialog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.artemissoftware.common.R
import com.artemissoftware.common.composables.icon.FGCircularIcon
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.common.theme.Purple80

//@Composable
//fun FGDialog() {
//
//}


@Composable
fun FGDialog__a(openDialogCustom: MutableState<Boolean>, dialogType: DialogType) {
    Dialog(onDismissRequest = { openDialogCustom.value = false}) {
        FGDialog_(
            openDialogCustom = openDialogCustom,
            dialogType
        )
    }
}

@Composable
private fun FGDialog_(
//    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    dialogType: DialogType
){
    val imageContent: @Composable () -> Unit = when{

        dialogType.imageId != null ->{
            {
                Image(
                    painter = painterResource(id = dialogType.imageId),
                    contentDescription = null, // decorative
//                contentScale = ContentScale.Fit,
                    colorFilter  = ColorFilter.tint(
                        color = dialogType.iconColor
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .height(70.dp)
                        .fillMaxWidth(),

                    )
            }
        }
        dialogType.icon != null ->{
            {
                FGCircularIcon(
                    modifier = Modifier
                            .padding(top = 35.dp),
                     icon = dialogType.icon,
                     iconColor = dialogType.iconColor,
                     backgroundAlpha = 0.1F,
                    size = 70.dp,
                    iconPadding = 12.dp
                )


            }
        }
        else ->{
            {}
        }
    }

    FGDialog(
        dialogType = dialogType,
        imageContent = imageContent
    )

}



//Layout
@Composable
private fun FGDialog(
//    modifier: Modifier = Modifier,
//    openDialogCustom: MutableState<Boolean>
    dialogType: DialogType,
    imageContent: @Composable () -> Unit
){
    Card(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 5.dp, bottom = 10.dp),
        elevation = 8.dp
    ) {
        Column {


            imageContent()


            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = dialogType.title,
                    style = FGStyle.TextAlbertSansBold,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = dialogType.description,
                    style = FGStyle.TextAlbertSans,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                )
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(dialogType.mainColor),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(onClick = {
//                    openDialogCustom.value = false
                }) {

                    Text(
                        "Not Now",
                        style = FGStyle.TextAlbertSansBold,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
                TextButton(onClick = {
//                    openDialogCustom.value = false
                }) {
                    Text(
                        "Allow",
                        style = FGStyle.TextAlbertSansBold,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun FGDialogImagePreview(){

    val dialogTypeSuccess = DialogType.Success(
        title =  "Get updates",
        description = "Allow permission to send notifications every day of the year",
        icon = Icons.Filled.Build
    )

    val successDialog = remember { mutableStateOf(false) }

    FGDialog_(successDialog, dialogTypeSuccess)

    //CustomDialogUI(openDialogCustom = mutableStateOf(false))
//    FGDialog(
//        imageId = R.drawable.ic_android,
//        tint = Color.Magenta
//    )
}
