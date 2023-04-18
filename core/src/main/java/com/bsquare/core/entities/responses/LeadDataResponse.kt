package com.bsquare.core.entities.responses

import com.bsquare.core.entities.Leads
import com.google.gson.annotations.SerializedName

data class LeadDataResponse(
    @SerializedName("success")
    val status: Boolean,
    val message: String,
    val leads: List<Leads>
)
