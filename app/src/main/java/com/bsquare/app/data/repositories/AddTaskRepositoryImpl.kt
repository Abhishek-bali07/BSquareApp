package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.followup.AddTaskRepository
import com.bsquare.core.entities.Assign
import com.bsquare.core.entities.Task
import com.bsquare.core.entities.TaskDetailData
import com.bsquare.core.entities.Type
import com.bsquare.core.entities.responses.TaskInfoResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class AddTaskRepositoryImpl @Inject constructor() : AddTaskRepository{
    override suspend fun getInitialData(userId: String
    ): Resource<TaskInfoResponse> {
        delay(2000L)
        return  Resource.Success(
            TaskInfoResponse(
                status = true,
                message = "Success",
                detail = TaskDetailData(
                    taskFor = listOf(
                        Task(
                            taskId = "1",
                            taskName = "Task1",
                            isSelected = true
                        ),
                        Task(
                        taskId = "2",
                        taskName = "Task2",
                        isSelected = true
                    ),
                        Task(
                        taskId = "3",
                        taskName = "Task3",
                        isSelected = true
                        )
                    ),
                    taskType = listOf(
                        Type(
                            typeId = "1",
                            typeName = "type1",
                            isSelected = false
                        ),
                        Type(
                            typeId = "2",
                            typeName = "type2",
                            isSelected = false
                        ),
                        Type(
                            typeId = "3",
                            typeName = "type3",
                            isSelected = false
                        )),
                    taskAssign = listOf(
                        Assign(
                            assignId = "1",
                            assignName = "assign1",
                            isSelected = true
                        ),
                        Assign(
                            assignId = "2",
                            assignName = "assign2",
                            isSelected = true
                        ),
                        Assign(
                            assignId = "3",
                            assignName = "assign3",
                            isSelected = true
                        )
                    )
                )
        )
        )
    }
}