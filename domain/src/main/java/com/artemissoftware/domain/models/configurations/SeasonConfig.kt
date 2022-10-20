package com.artemissoftware.domain.models.configurations

data class SeasonConfig(
    val backgroundColor: String,
    val chipColor: ChipColorConfig,
    val seasonMessage: String? = null
)
