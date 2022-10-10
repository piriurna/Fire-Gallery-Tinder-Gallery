package com.artemissoftware.domain.models

data class SeasonConfig(
    val backgroundColor: String,
    val chipColor: ChipColorConfig,
    val seasonMessage: String? = null
)
