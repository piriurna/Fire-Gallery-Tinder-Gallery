package com.artemissoftware.firegallery.screens.splash.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.theme.Orange
import com.artemissoftware.common.theme.RedOrange
import com.artemissoftware.firegallery.R
import com.artemissoftware.firegallery.screens.SplashScreen

@Composable
fun Logo(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
    borderColor: Color = RedOrange
) {

    Box(
        modifier = modifier
            .size(180.dp)
            .clip(CircleShape)
            .background(color = Orange)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = CircleShape
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_flame),
            contentDescription = "Compose image",
            colorFilter =  ColorFilter.tint(color = Red),
            modifier = Modifier
                .size(140.dp)
                .align(alignment = Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LogoPreview() {

    Logo()
}