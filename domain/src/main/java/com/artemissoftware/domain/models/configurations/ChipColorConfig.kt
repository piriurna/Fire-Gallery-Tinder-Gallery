package com.artemissoftware.domain.models.configurations

data class ChipColorConfig(
    val startBorderColor: String,
    val endBorderColor: String,
    val iconColor: String
){

    companion object{

        val mockChipColorConfig = ChipColorConfig(startBorderColor = "#00aeef", endBorderColor = "#9df7fa", iconColor="#faf18e")

    }

}
