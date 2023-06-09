package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.lead.CompanyDetailsRepository
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
   private val prefs: AppStore,
   private val  repository: CompanyDetailsRepository
){
   fun InitialDetails(leadID: String) = flow<Data> {
       emit(Data(EmitType.Loading, value = true))
       when(val response = repository.getInitialData(leadID,prefs.userId())){
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                          emit(Data(type = EmitType.LeadsDetails, value = details))
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


    fun addDetails(leadID: String, text: String, tabId: String) = flow<Data> {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.addTimelineData(leadID,prefs.userId(), tabId, text)){
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status && isAdded){
                        true ->{
                            emit(Data(type = EmitType.LeadAdded, value = this.isAdded))
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


    fun removeDetails(leadID: String, text: String, tabId: String) = flow<Data> {
        emit(Data(EmitType.Loading, value = true))
        when(val response =  repository.removeTimelineData(leadID, prefs.userId(), tabId, text)){
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply{
                    when(status && isRemoved){
                        true ->{
                            emit(Data(type = EmitType.LeadRemoved, value = this.isRemoved))
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

}