package com.bsquare.core.entities

data class ActivityDetailData(
    val activityFor: List<ActivityLeads>,
    val activityType: List<ActivityType>,
    val notes: List<NotesData>
)
