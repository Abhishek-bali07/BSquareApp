package com.bsquare.core.domain.repositories.lead

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.CompanyDetailsResponse

interface CompanyDetailsRepository {
        suspend fun  getInitialData(userId: String, leadID: String) : Resource<CompanyDetailsResponse>
}