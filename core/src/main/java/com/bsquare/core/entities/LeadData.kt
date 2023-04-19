package com.bsquare.core.entities

data class LeadData(
    val id: String,
    val companyName: String,
    val leadAmount: String,
    val type: String,
    val companyIconUrl:String,
    val isNew :Boolean,
    val companyNumber:String,
)
