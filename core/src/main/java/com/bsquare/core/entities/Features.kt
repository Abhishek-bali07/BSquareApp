package com.bsquare.core.entities

data class Feature(
    val identityIconUrl: String,
    val type: String,
    val bgColor: String,
    val quantity: Int = 0,
    )
