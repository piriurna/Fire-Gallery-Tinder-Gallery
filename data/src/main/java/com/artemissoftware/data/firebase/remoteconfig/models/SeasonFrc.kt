package com.artemissoftware.data.firebase.remoteconfig.models

import com.artemissoftware.domain.util.SeasonType
import com.google.gson.annotations.SerializedName

data class SeasonFrc(
    @SerializedName("backgroundColor")
    val backgroundColor: String,
    @SerializedName("chipColor")
    val chipColor: ChipColorFrc,
    @SerializedName("seasonMessage")
    val seasonMessage: String,

    @SerializedName("spring")
    val spring: SeasonDetailFrc,
    @SerializedName("summer")
    val summer: SeasonDetailFrc,
    @SerializedName("autumn")
    val autumn: SeasonDetailFrc,
    @SerializedName("winter")
    val winter: SeasonDetailFrc
){

    fun getSeason(seasonType: SeasonType): SeasonDetailFrc{
        return when(seasonType){
            SeasonType.SPRING -> spring
            SeasonType.SUMMER -> summer
            SeasonType.AUTUMN -> autumn
            SeasonType.WINTER -> winter
        }
    }


}
