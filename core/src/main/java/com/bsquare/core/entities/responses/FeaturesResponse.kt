package com.bsquare.core.entities.responses

import com.bsquare.core.entities.Feature
import com.google.gson.annotations.SerializedName

 data class FeaturesResponse (
     @SerializedName("success")
     val status: Boolean,
     val message: String,
     val feature: List<Feature>

)