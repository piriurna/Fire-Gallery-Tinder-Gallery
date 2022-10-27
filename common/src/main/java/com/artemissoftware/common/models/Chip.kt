package com.artemissoftware.common.models

import com.artemissoftware.common.extensions.hextoColor

data class Chip(
    val text: String,
    private val startBorderColor: String = "#f8d708",
    private val endBorderColor: String = "#f8d708",
    private val iconColor: String = "#f8d708"
) {

    fun getStartBorderColor() = startBorderColor.hextoColor()
    fun getEndBorderColor() = endBorderColor.hextoColor()
    fun getIconColor() = iconColor.hextoColor()

    companion object{

        val mockChips = listOf(
            Chip("Apple", "#f8d708", "#38761d", "#66ccff"),
            Chip("Bananas", "#A9d708", "#38761d", "#66BBBB"),
            Chip("Cherries", "#f8d708", "#AA761d", "#AAccff")
        )
    }
}
