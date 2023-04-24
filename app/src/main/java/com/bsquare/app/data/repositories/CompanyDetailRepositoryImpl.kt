package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.lead.CompanyDetailsRepository
import com.bsquare.core.entities.Label
import com.bsquare.core.entities.Latlong
import com.bsquare.core.entities.LeadDetailsData
import com.bsquare.core.entities.Status
import com.bsquare.core.entities.responses.CompanyDetailsResponse
import com.bsquare.core.entities.responses.RemoveTimelineResponse
import com.bsquare.core.entities.responses.UpdateCompanyDetailsResponse
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
                companyEmail = "Infosys@email.com",
                leadDescription = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                latLong = Latlong(
                    lat = 22.5648,
                    lng = 88.2356
                ),
                leadLabel = listOf(Label(
                        labelId = "1",
                        labelName = "WARM",
                        isSelected = true
                    ),
                 Label(
                    labelId = "2",
                    labelName = "Hot",
                    isSelected = true
                ),
                Label(
                labelId = "3",
                labelName = "Cold",
                isSelected = true
            )),
                leadStatus = listOf(Status(
                    statusId = "1",
                    statusName = "NEW",
                    isSelected = true
                ),Status(
                    statusId = "1",
                    statusName = "Old",
                    isSelected = true
                )),
                activity = listOf("oiuuyv","oihoihoib"),
                task = emptyList(),
                info = emptyList(),
                note = emptyList()



            )
        ))
    }

    override suspend fun addTimelineData(
        userId: String,
        leadID: String,
        tabId: String,
        text: String
    ): Resource<UpdateCompanyDetailsResponse> {
        delay(2000L)
        return  Resource.Success(
            UpdateCompanyDetailsResponse(
                status = true,
                message = "Success",
                isAdded = true
            )
        )
    }

    override suspend fun removeTimelineData(
        userId: String,
        leadID: String,
        tabId: String,
        text: String
    ): Resource<RemoveTimelineResponse> {
        delay(2000L)
        return  Resource.Success(
            RemoveTimelineResponse(
                status = true,
                message = "Success",
                isRemoved = true
            )
        )
    }


}