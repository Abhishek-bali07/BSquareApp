package com.bsquare.core.domain.repositories.dashboard

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.FeaturesResponse

interface DashboardRepository {

    suspend fun  seefeatures(userId: String) :Resource<FeaturesResponse>
}