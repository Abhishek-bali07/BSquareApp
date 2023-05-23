package com.bsquare.core.entities

import com.bsquare.core.common.constants.Destination

data class ActivityDetails(
    val activityFor :String,
    val activityType : String,
    val activityDate : String,
    val phoneNumber :String,
    val activityTime: String,
    val companyNames: String,
    val activityNotes: String,
    val destination:Destination
)
