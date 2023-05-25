package com.bsquare.core.entities.responses

import com.bsquare.core.entities.FilterTypeData
import com.google.gson.annotations.SerializedName

data class FollowFilterResponse(
    @SerializedName("success")
    val status: Boolean,
    val message: String,
    val details: FilterTypeData
)
