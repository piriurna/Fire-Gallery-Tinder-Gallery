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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.R
import com.artemissoftware.common.composables.icon.FGCircularIcon
import com.artemissoftware.common.theme.FGStyle
import com.artemissoftware.common.theme.Purple80

//@Composable
//fun FGDialog() {
//
//}


//@Composable
//fun CustomDialog(openDialogCustom: MutableState<Boolean>) {
//    Dialog(onDismissRequest = { openDialogCustom.value = false}) {
//        CustomDialogUI(openDialogCustom = openDialogCustom)
//    }
//}


@Composable
private fun FGDialog(
//    modifier: Modifier = Modifier,
//    openDialogCustom: MutableState<Boolean>
//    content: @Composable () -> Unit
    @DrawableRes imageId: Int,
    tint: Color = Color.Black
){

    FGDialog(
        content = {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null, // decorative
//                contentScale = ContentScale.Fit,
                colorFilter  = ColorFilter.tint(
                    color = tint
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

                )

        }
    )

}

@Composable
private fun FGDialog(
//    modifier: Modifier = Modifier,
//    openDialogCustom: MutableState<Boolean>
//    content: @Composable () -> Unit
    icon: ImageVector,
    iconColor: Color = Color.Black,
){

    FGDialog(
        content = {
            FGCircularIcon(
                icon = icon,
                iconColor = iconColor,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),
            )

        }
    )

}


//Layout
@Composable
private fun FGDialog(
//    modifier: Modifier = Modifier,
//    openDialogCustom: MutableState<Boolean>
    content: @Composable () -> Unit
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


            content()


            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Get Updates",
                    style = FGStyle.TextAlbertSansBold,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Allow Permission to send you notifications when new art styles added.",
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
                    .background(Purple80),
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


@Preview(name="Image Custom Dialog")
@Composable
fun FGDialogImagePreview(){
    //CustomDialogUI(openDialogCustom = mutableStateOf(false))
    FGDialog(
        imageId = R.drawable.ic_android,
        tint = Color.Magenta
    )
}

@Preview(name="Vector Custom Dialog")
@Composable
fun FGDialogVectorPreview(){
    //CustomDialogUI(openDialogCustom = mutableStateOf(false))
    FGDialog(
        icon = Icons.Filled.Build,
        iconColor = Color.Green
    )
}