package com.artemissoftware.common.util

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Stable

object ScreenTransitions {


    @Stable
    @ExperimentalAnimationApi
    fun slideInRight(durationMillis: Int = 300): EnterTransition {
        return slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(durationMillis))
    }

    @Stable
    @ExperimentalAnimationApi
    fun slideInLeft(durationMillis: Int = 300): EnterTransition {
        return slideInHorizontally(animationSpec = tween(durationMillis))
    }

    @Stable
    @ExperimentalAnimationApi
    fun slideOutLeft(durationMillis: Int = 300): ExitTransition {
        return slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(durationMillis))
    }

    @Stable
    @ExperimentalAnimationApi
    fun slideOutVertically(durationMillis: Int = 300): ExitTransition {
        return slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(durationMillis))
    }

    @Stable
    @ExperimentalAnimationApi
    fun slideOutRight(durationMillis: Int = 300): ExitTransition {
        return slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(durationMillis))
    }


}