package com.bsquare.core.domain.repositories.lead

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.LeadFilterResponse

interface FilterPageRepository {



    suspend fun getInitialData(userId: String) :Resource<LeadFilterResponse>
}