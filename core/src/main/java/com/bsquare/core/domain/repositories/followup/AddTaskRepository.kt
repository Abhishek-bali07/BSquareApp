package com.bsquare.core.domain.repositories.followup

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.TaskInfoResponse

interface AddTaskRepository {


    suspend fun  getInitialData(userId:String) :  Resource<TaskInfoResponse>
}