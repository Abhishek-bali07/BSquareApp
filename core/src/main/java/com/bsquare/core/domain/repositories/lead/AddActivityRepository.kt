package com.bsquare.core.domain.repositories.lead

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.ActivityListResponse
import com.bsquare.core.entities.responses.AddNewActivityResponse

interface AddActivityRepository {
     suspend fun getInitialData(userId:String) : Resource<ActivityListResponse>

     suspend fun addActivityData(
          activityFor :String,
          activityType : String,
          activityDate : String,
          phoneNumber :String,
          activityTime: String,
          companyNames: String,
          activityNotes: String,
          userId: String
     ) :Resource<AddNewActivityResponse>
}