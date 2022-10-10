package com.artemissoftware.domain.models.configurations

import com.artemissoftware.domain.models.configurations.ChipColorConfig

data class SeasonConfig(
    val backgroundColor: String,
    val chipColor: ChipColorConfig,
    val seasonMessage: String? = null
)
