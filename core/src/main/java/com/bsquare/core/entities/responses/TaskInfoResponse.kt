package com.bsquare.core.entities.responses

import com.bsquare.core.entities.TaskDetailData
import com.google.gson.annotations.SerializedName

data class TaskInfoResponse(

    @SerializedName("success")
    val status: Boolean,
    val message: String,
    val detail: TaskDetailData
)
