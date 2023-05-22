package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.lead.AddActivityRepository
import com.bsquare.core.utils.handleFailedResponse
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddActivityUseCase  @Inject constructor(
    private  val prefs: AppStore,
    private val repository: AddActivityRepository
){
    fun InitialDetails() = flow<Data> {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getInitialData(prefs.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){

                        true->{
                            emit(Data(type = EmitType.AddActivityDetails, value = detail))
                            emit(Data(type = EmitType.AddNotesDetails, value = detailNotes))
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

    fun addActivity(
        activityFor :String,
        activityType : String,
        activityDate : String,
        phoneNumber :String,
        activityTime: String,
        companyNames: String,
        activityNotes: String
    ) = flow<Data> {
        emit(Data(EmitType.Loading, true))
        when(val response = repository.addActivityData(
            activityFor = activityFor,
            activityType = activityType,
            activityDate= activityDate,
            phoneNumber = phoneNumber,
            activityTime = activityTime,
            companyNames = companyNames,
            activityNotes = activityNotes,
            prefs.userId()
        )){
            is Resource.Success ->{
                emit(Data(EmitType.Loading,false))
                response.data?.apply {
                    when(status){

                        true ->{
                            emit(Data(type = EmitType.addActivity, message))
                            emit(Data(type = EmitType.Navigate, Destination.CompanyDetailScreen))
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

            else -> {}
        }
    }



}