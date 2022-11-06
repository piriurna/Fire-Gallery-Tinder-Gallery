package com.artemissoftware.data.firebase.remoteconfig.models


import com.google.gson.annotations.SerializedName

data class SeasonDetailFrc(
    @SerializedName("chipColor")
    val chipColor: ChipColorFrc
)