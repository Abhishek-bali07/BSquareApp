package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.lead.FilterPageRepository
import com.bsquare.core.entities.*
import com.bsquare.core.entities.responses.LeadFilterResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor() : FilterPageRepository {
    override suspend fun getInitialData(
        userId: String): Resource<LeadFilterResponse> {
        delay(2000L)
        return  Resource.Success(LeadFilterResponse(
            status = true,
            message = "Success",
            details = FilterTypeData(
                dataLead = listOf(
                    DataLead(
                    dataLeadId = "1",
                    dataLeadName = "Yesterday",
                    isSelected = true
                ),
                DataLead(
                        dataLeadId = "2",
                        dataLeadName = "Last 7 days",
                        isSelected = false
                ),
                DataLead(
                        dataLeadId = "3",
                        dataLeadName = "Last 30 days",
                        isSelected = false
                )



                ),
                dataLabel = listOf(
                    Label(
                        labelId = "1",
                        labelName = "Hot",
                        isSelected = true
                    ),
                    Label(
                        labelId = "2",
                        labelName = "Warm",
                        isSelected = false
                    ),
                    Label(
                        labelId = "3",
                        labelName = "Cold",
                        isSelected = false
                    )
                ),
                dataStatus = listOf(
                    Status(
                        statusId = "1",
                        statusName = "New Lead",
                        isSelected = false
                    ),
                    Status(
                        statusId = "2",
                        statusName = "Closed",
                        isSelected = false
                    ),
                    Status(
                        statusId = "3",
                        statusName = "Disqualified",
                        isSelected = false
                    ),
                    Status(
                        statusId = "4",
                        statusName = "Not Reachable",
                        isSelected = false
                    ),
                    Status(
                        statusId = "5",
                        statusName = "Archived",
                        isSelected = false
                    ),
                    Status(
                        statusId = "6",
                        statusName = "Sale",
                        isSelected = false
                    ),
                    Status(
                        statusId = "7",
                        statusName = "Working",
                        isSelected = false
                    ),
                ),
                dataSource = listOf(
                    DataSource(
                        dataSourceId ="1",
                        dataSourceName ="Google-Lead Forms",
                        isSelected = false
                    ),
                    DataSource(
                        dataSourceId ="2",
                        dataSourceName ="Indiamart",
                        isSelected = false
                    ),
                    DataSource(
                        dataSourceId ="3",
                        dataSourceName ="Justdial",
                        isSelected = false
                    ),
                    DataSource(
                        dataSourceId ="4",
                        dataSourceName ="Zapier",
                        isSelected = false
                    ),
                    DataSource(
                        dataSourceId ="5",
                        dataSourceName ="Facebook",
                        isSelected = false
                    ),
                    DataSource(
                        dataSourceId ="6",
                        dataSourceName ="Tradeindia",
                        isSelected = false
                    ),
                    DataSource(
                        dataSourceId ="7",
                        dataSourceName ="Lead Form",
                        isSelected = false
                    ),




                ),
                teamMember = listOf(
                    TeamMember(
                        teamMemberId = "1",
                        teamMemberName ="Ravi",
                        isSelected = false
                    ),

                    TeamMember(
                        teamMemberId = "2",
                        teamMemberName ="Tejinder",
                        isSelected = false
                    ),

                    TeamMember(
                        teamMemberId = "3",
                        teamMemberName ="Govind",
                        isSelected = false
                    ),
                )
            )
        ))

    }

}