package com.bsquare.core.entities

data class FilterTypeData(
    val dataLead : List<DataLead>,
    val dataLabel: List<Label>,
    val dataStatus:List<Status>,
    val dataSource: List<DataSource>,
    val teamMember: List<TeamMember>,

)
