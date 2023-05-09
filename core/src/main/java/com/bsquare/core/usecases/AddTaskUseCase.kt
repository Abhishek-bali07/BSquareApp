package com.bsquare.core.usecases


import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.followup.AddTaskRepository
import com.bsquare.core.utils.handleFailedResponse
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private  val prefs: AppStore,
    private val repository:AddTaskRepository
){


    fun InitialDetails() = flow<Data> {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getInitialData(prefs.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true -> {
                            emit(Data(type = EmitType.TaskDetailData, value = detail))
                        }
                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }
            else -> {}
        }
    }



    fun addTask(
        taskFor  : String,
        taskType:String,
        dueDate:String,
        customDate:String,
        taskTime: String,
        taskRepeat:String,
        taskAssign:String,
        taskDescription:String,
        ) = flow<Data>{
        emit(Data(EmitType.Loading, true))
        when(val response  = repository.addTaskData(
                taskFor = taskFor,
                taskType= taskType,
                dueDate = dueDate,
                customDate = customDate,
                taskTime = taskTime,
                taskRepeat = taskRepeat,
                taskAssign = taskAssign,
                taskDescription =taskDescription,
                prefs.userId()
        )){
            is Resource.Success ->{
                emit(Data(EmitType.Loading,false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(type = EmitType.getResponse, message))
                            emit(Data(type = EmitType.Navigate, Destination.FollowupScreen))
                        }
                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }
            is Resource.Error -> {
                emit(Data(EmitType.Loading, false))
                handleFailedResponse(
                    response = response,
                    message = response.message,
                    emitType = EmitType.NetworkError
                )
            }

            else -> {

            }
        }
    }




}