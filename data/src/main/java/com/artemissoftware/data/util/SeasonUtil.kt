package com.artemissoftware.data.util

import com.artemissoftware.domain.util.SeasonType

object SeasonUtil {

    fun findSeason(month: Int): SeasonType {
        return when (month) {
            12, 1, 2 -> SeasonType.WINTER
            3, 4, 5 -> SeasonType.SPRING
            6, 7, 8 -> SeasonType.SUMMER
            9, 10, 11 -> SeasonType.AUTUMN
            else -> SeasonType.WINTER
        }
    }

}