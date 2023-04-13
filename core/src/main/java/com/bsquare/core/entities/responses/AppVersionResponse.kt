package com.bsquare.core.entities.responses

import com.bsquare.core.entities.AppVersion
import com.google.gson.annotations.SerializedName


data class AppVersionResponse(
    @SerializedName("success")
    val status: Boolean,
    val message: String,
    val appVersion: AppVersion
)