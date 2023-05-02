package com.bsquare.core.entities.responses

import com.bsquare.core.entities.Follow
import com.google.gson.annotations.SerializedName

data class FollowupResponse(
    @SerializedName("success")
    val status: Boolean,
    val message: String,
    val today:List<Follow>,
    val upcoming:List<Follow>,
    val overdue:List<Follow>,
    val done: List<Follow>
)
