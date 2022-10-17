package com.artemissoftware.common.extensions

import androidx.navigation.NavHostController

fun NavHostController.changeGraph(route: String) {
    navigate(route) {
        if (this@changeGraph.backQueue.isNotEmpty()) {
            this@changeGraph.backQueue.last().destination.route?.let { route ->
                popUpTo(route) {
                    inclusive = true
                }
            }
        }
    }
}