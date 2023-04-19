package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.lead.CompanyDetailsRepository
import com.bsquare.core.entities.Latlong
import com.bsquare.core.entities.LeadData
import com.bsquare.core.entities.LeadDetailsData
import com.bsquare.core.entities.responses.CompanyDetailsResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class DeatilRepositoryImpl @Inject constructor() : CompanyDetailsRepository {

    override suspend fun getInitialData(
        userId: String,
        leadID: String
    ): Resource<CompanyDetailsResponse> {
        delay(2000L)
        return  Resource.Success(CompanyDetailsResponse(
            status = true,
            message = "Success",
            details = LeadDetailsData(
                companyName = "Infosys",
                leadAmount = "250000",
                companyNumber = "9733745623",
                leadDescription = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                latLong = Latlong(
                    lat = 22.5648,
                    lng = 88.2356
                )



            )
        ))
    }

}