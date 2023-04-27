package com.bsquare.core.domain.repositories.lead

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.AddNewLeadResponse
import com.bsquare.core.entities.responses.CurrentLocationResponse

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
        lat: Double,
        lng: Double,
        userId: String,
    ) : Resource<AddNewLeadResponse>


    suspend fun currentLocation(userId: String, lat: Double, lng: Double): Resource<CurrentLocationResponse>
    //suspend fun submitFCMToken(lastFCMToken: String, userId: String) : Resource<Any>

}