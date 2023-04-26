package com.bsquare.core.domain.repositories.lead

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.AddNewLeadResponse

interface AddLeadRepository {

    suspend fun addNewLead(
        clientName: String,
        emailId: String,
        phoneNumber: String,
        alternateNumber: String,
        companyName: String,
        website: String,
        saleValue: String,
        notes: String,
        userId: String
    ) : Resource<AddNewLeadResponse>
}