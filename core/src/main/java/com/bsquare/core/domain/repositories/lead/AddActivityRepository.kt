package com.bsquare.core.domain.repositories.lead

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.ActivityListResponse

interface AddActivityRepository {
     suspend fun getInitialData(userId:String) : Resource<ActivityListResponse>
}