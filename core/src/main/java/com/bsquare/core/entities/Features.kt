package com.bsquare.core.entities

data class Feature(
    val feature_Id:String,
    val identityIconUrl: String,
    val type: String,
    val bgColor: String,
    val quantity: Int = 0,
    )
