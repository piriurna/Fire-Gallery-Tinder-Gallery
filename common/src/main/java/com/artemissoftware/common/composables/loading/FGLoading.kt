package com.artemissoftware.common.composables.loading

import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.R
import com.artemissoftware.common.composables.animations.FGLottieLoader
import com.artemissoftware.common.theme.secondaryBackground

@Composable
fun FGLoading(
    isLoading: Boolean,
    @RawRes lottieId: Int = R.raw.gallery_photo,
) {

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = isLoading
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = false, onClick = {})
                .background(MaterialTheme.colors.secondaryBackground.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            FGLottieLoader(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 40.dp),
                id = lottieId
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun FGLoadingPreview() {
    FGLoading(isLoading = true)
}