package com.bsquare.core.domain.repositories.followup

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.AddNewTaskResponse
import com.bsquare.core.entities.responses.TaskInfoResponse

interface AddTaskRepository {


    suspend fun  getInitialData(userId:String) :  Resource<TaskInfoResponse>



    suspend fun addTaskData(
        taskFor  : String,
        taskType:String,
        dueDate:String,
        customDate:String,
        taskTime: String,
        taskRepeat:String,
        taskAssign:String,
        taskDescription:String,
        userId: String,

    ) : Resource<AddNewTaskResponse>
}