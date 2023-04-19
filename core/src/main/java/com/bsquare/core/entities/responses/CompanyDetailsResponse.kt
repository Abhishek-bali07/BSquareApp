package com.bsquare.core.entities.responses

import com.bsquare.core.entities.LeadData
import com.bsquare.core.entities.LeadDetailsData
import com.google.gson.annotations.SerializedName

data class CompanyDetailsResponse(

    @SerializedName("success")
    val status: Boolean,
    val message: String,
    val details: LeadDetailsData
)
