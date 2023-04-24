package com.bsquare.core.entities

data class LeadDetailsData(
    val leadDescription: String,
    val companyName: String,
    val leadAmount: String,
    val leadStatus: List<Status>,
    val leadLabel :List<Label>,
    val companyNumber:String,
    val companyEmail: String,
    val latLong: Latlong,


    val activity: List<String>,
    val task: List<String>,
    val info :List<String>,
    val note:List<String>

)
