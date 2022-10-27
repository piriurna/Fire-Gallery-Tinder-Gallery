package com.artemissoftware.common.composables.animations

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.*
import com.artemissoftware.common.R

@Composable
fun FGLottieLoader(
    @RawRes id: Int,
    modifier: Modifier = Modifier
) {


    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(id)
        )

    LottieAnimation(
        composition = compositionResult.value,
        isPlaying  = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.5f,
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
private fun LottieLoaderPreview() {
    Column(Modifier.fillMaxSize()) {

        FGLottieLoader(id = R.raw.gallery_phone)
    }
}
