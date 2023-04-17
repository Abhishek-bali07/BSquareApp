package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.dashboard.DashboardRepository
import com.bsquare.core.entities.Feature
import com.bsquare.core.entities.responses.FeaturesResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor() : DashboardRepository {
    override suspend fun seefeatures(
        userId: String,
        selectedDate: String
    ): Resource<FeaturesResponse> {
        delay(2000L)
        return Resource.Success(
            FeaturesResponse(
                status = true,
                message = "Success",
                feature = MutableList(4) {idx->
                    Feature(
                        type = "Meetings",
                        quantity = 20,
                        bgColor = "#1AB1B0",
                        identityIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                    )
                }

            )
        )
    }


}