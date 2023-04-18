package com.bsquare.core.domain.repositories.lead

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.LeadDataResponse

interface LeadRepository {

    suspend fun initialLeadData(userId: String) :Resource<LeadDataResponse>
}