package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.dashboard.DashboardRepository
import com.bsquare.core.entities.Feature
import com.bsquare.core.entities.ShortDetails
import com.bsquare.core.entities.responses.FeaturesResponse
import com.bsquare.core.entities.responses.userDetailResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor() : DashboardRepository {
    override suspend fun getUserDetails(
        userId: String
    ): Resource<userDetailResponse> {
        delay(2000L)
        return Resource.Success(
            userDetailResponse(
                status = true,
                message = "Success",
                shortDetails = ShortDetails(
                    userName = "Abhishek B",
                    userImage = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                    userNumber = "8885552222"

                )

            )
        )
    }

    override suspend fun seefeatures(
        userId: String,
        selectedDate: String
    ): Resource<FeaturesResponse> {
        delay(2000L)
        return Resource.Success(
            FeaturesResponse(
                status = true,
                message = "Success",
                feature = listOf(
                    Feature(
                        feature_Id = "1",
                        type = "Meetings",
                        quantity = 25,
                        bgColor = "#03A0E2",
                        identityIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                    ),
                    Feature(
                        feature_Id = "2",
                        type = "Tasks",
                        quantity = 25,
                        bgColor = "#8576FD",
                        identityIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                    ),
                    Feature(
                        feature_Id = "3",
                        type = "Follow-up",
                        quantity = 25,
                        bgColor = "#F95A7B",
                        identityIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                    ),
                    Feature(
                        feature_Id = "4",
                        type = "Leads",
                        quantity = 20,
                        bgColor = "#1AB1B0",
                        identityIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                    )
                )
//                feature = MutableList(4) {idx->
//                    Feature(
//                        feature_Id = "ID_${idx+1}",
//                        type = "Leads",
//                        quantity = 20,
//                        bgColor = "#1AB1B0",
//                        identityIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
//                    )
//                }

            )
        )
    }


}