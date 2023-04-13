package com.bsquare.core.entities.responses

import com.google.gson.annotations.SerializedName


data class LoginVerifyResponse(
    @SerializedName("success")
    val status: Boolean,
    val message: String,
)