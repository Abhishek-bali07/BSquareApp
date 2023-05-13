package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.lead.FilterPageRepository
import com.bsquare.core.entities.DataLead
import com.bsquare.core.entities.FilterTypeData
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
                dataLabel = listOf(),
                dataSource = listOf(),
                dataStatus = listOf(),
                teamMember = listOf()
            )
        ))

    }

}