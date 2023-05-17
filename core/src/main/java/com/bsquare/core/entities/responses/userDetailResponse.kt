package com.bsquare.core.entities.responses

import com.bsquare.core.entities.ShortDetails
import com.google.gson.annotations.SerializedName

data class userDetailResponse(
    @SerializedName("success")
    val status: Boolean,
    val message: String,
    val shortDetails: ShortDetails

)
