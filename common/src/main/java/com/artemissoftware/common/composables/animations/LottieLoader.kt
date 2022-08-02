package com.artemissoftware.common.composables.animations

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.*
import com.artemissoftware.common.R

@Composable
fun LottieLoader(
    @RawRes id: Int
) {


    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(id)
            //LottieCompositionSpec.Asset("lottie/gallery_phone.json")
        )

/*
    val color by derivedStateOf { Color.Red }

    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = color.toArgb(), keyPath = arrayOf(
                "compass needle",
                "Shape 1",
                "Fill 1"
            )
        ),

        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = color.toArgb(), keyPath = arrayOf(
                "donut",
                "Group 1",
                "Fill 1"
            )
        ),
        rememberLottieDynamicProperty(
            property = LottieProperty.OPACITY,
            value = 50,
            keyPath = arrayOf(
                "compass needle",
                "Shape 1",
                "Fill 1"
            )
        ),
    )
*/
    LottieAnimation(
        composition = compositionResult.value,
        isPlaying  = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
//        progress = progress,
//        dynamicProperties = dynamicProperties,
//        modifier = Modifier.padding(all = 50.dp)
    )

}

@Preview(showBackground = true)
@Composable
private fun LottieLoaderPreview() {
    Column(Modifier.fillMaxSize()) {

        LottieLoader(id = R.raw.gallery_phone)
    }
}
