package com.bsquare.core.domain.repositories.dashboard

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.FeaturesResponse
import com.bsquare.core.entities.responses.userDetailResponse

interface DashboardRepository {

    suspend fun getUserDetails(userId: String) :Resource<userDetailResponse>

    suspend fun  seefeatures(userId: String, selectedDate:String) :Resource<FeaturesResponse>


}