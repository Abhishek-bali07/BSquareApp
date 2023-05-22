package com.bsquare.core.entities.responses

import com.bsquare.core.entities.ActivityDetailData
import com.bsquare.core.entities.ActivityNotesDetails
import com.google.gson.annotations.SerializedName

data class ActivityListResponse(
    @SerializedName("success")
    val status : Boolean,
    val message:String,
    val detail: ActivityDetailData,
    val detailNotes: ActivityNotesDetails

)
