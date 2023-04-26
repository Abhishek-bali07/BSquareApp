package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.lead.AddLeadRepository
import com.bsquare.core.utils.handleFailedResponse
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddLeadUseCase @Inject constructor(
    private val prefs: AppStore,
    private  val repository: AddLeadRepository
){
    fun AddLead(
        clientName:String,
        emailId: String,
        phoneNumber:String,
        alternateNumber:String,
        companyName:String,
        website:String,saleValue:String,notes:String
    ) = flow<Data>{
        emit(Data(EmitType.Loading, true))
        when(val response = repository.addNewLead(
            clientName = clientName,
            emailId = emailId,
            phoneNumber = phoneNumber,
            alternateNumber = alternateNumber,
            companyName = companyName,
            website = website,saleValue=saleValue,notes = notes,prefs.userId()
        )){
            is Resource.Success ->{
                emit(Data(EmitType.Loading,false))
                response.data?.apply {
                    when(status){

                        true ->{
                            emit(Data(type = EmitType.Inform, message))
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