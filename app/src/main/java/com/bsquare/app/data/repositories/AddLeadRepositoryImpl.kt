package com.bsquare.app.data.repositories


import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.lead.AddLeadRepository
import com.bsquare.core.entities.responses.AddNewLeadResponse
import com.bsquare.core.entities.responses.CurrentLocationResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class AddLeadRepositoryImpl @Inject constructor() : AddLeadRepository {
    override suspend fun addNewLead(
        clientName: String,
        emailId: String,
        phoneNumber: String,
        alternateNumber: String,
        companyName: String,
        website: String,
        saleValue: String,
        notes: String,
        lat: Double,
        lng: Double,
        userId: String
    ): Resource<AddNewLeadResponse> {
        delay(2000L)
        return  Resource.Success(
            AddNewLeadResponse(
                status = true,
                message = "Success"
            )
        )
    }

    override suspend fun currentLocation(
        userId: String,
        lat: Double,
        lng: Double
    ): Resource<CurrentLocationResponse> {
        delay(2000L)
        return  Resource.Success(
            CurrentLocationResponse(
                status = true,
                message = "Suceess"
            )
        )
    }


}
