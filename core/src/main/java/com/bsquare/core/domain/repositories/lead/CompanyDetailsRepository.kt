package com.bsquare.core.domain.repositories.lead

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.CompanyDetailsResponse
import com.bsquare.core.entities.responses.RemoveTimelineResponse
import com.bsquare.core.entities.responses.UpdateCompanyDetailsResponse

interface CompanyDetailsRepository {
        suspend fun  getInitialData(userId: String, leadID: String) : Resource<CompanyDetailsResponse>


        suspend fun  addTimelineData(userId: String, leadID: String, tabId: String, text: String) : Resource<UpdateCompanyDetailsResponse>


        suspend fun  removeTimelineData(userId: String, leadID: String, tabId: String, text: String) : Resource<RemoveTimelineResponse>
}