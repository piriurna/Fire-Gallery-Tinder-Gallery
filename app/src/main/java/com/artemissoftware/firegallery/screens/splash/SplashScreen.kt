package com.artemissoftware.firegallery.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.common.composables.scaffold.FGScaffold
import com.artemissoftware.common.composables.scaffold.models.FGScaffoldState
import com.artemissoftware.firegallery.screens.splash.composables.Logo

@Composable
fun SplashScreen(
    scaffoldState: FGScaffoldState,
    onAnimationFinish: () -> Unit = {}
) {


    FGScaffold(
        fgScaffoldState = scaffoldState
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,

        ) {


            Logo(
                onAnimationFinish = {

                    onAnimationFinish.invoke()
                },
                modifier = Modifier
            )

        }

    }


}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {

    //SplashScreen()
}