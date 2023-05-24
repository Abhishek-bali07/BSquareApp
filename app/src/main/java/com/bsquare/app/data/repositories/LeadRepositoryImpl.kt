package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.lead.LeadRepository
import com.bsquare.core.entities.LeadData
import com.bsquare.core.entities.Leads
import com.bsquare.core.entities.responses.LeadDataResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class LeadRepositoryImpl @Inject constructor() : LeadRepository {
    override suspend fun initialLeadData(
        userId: String
    ): Resource<LeadDataResponse> {
        delay(2000L)
        return Resource.Success(
            LeadDataResponse(
                status = true,
                message = "Success",
                leads = MutableList(5) { idx ->
                    Leads(date = "27th, July, 2023",
                        leadData = MutableList(4) { idx ->
                            LeadData(
                                id = "ID_${idx+1}",
                                companyName = "INFOSYS",
                                type = "Hot",
                                typeColor = "#F5CBA7",
                                leadAmount = "2500",
                                isNew = true,
                                companyIconUrl = "https://www.v-xplore.com/dev/rohan/assets/png/user_icon.png",
                                companyNumber = "9733745623"
                            )
                        }
                    )
                }
            )
        )
    }
}