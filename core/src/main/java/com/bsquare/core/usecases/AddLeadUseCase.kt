package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.lead.AddLeadRepository
import com.bsquare.core.entities.Latlong
import com.bsquare.core.utils.handleFailedResponse
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddLeadUseCase @Inject constructor(
    private val prefs: AppStore,
    private  val repository: AddLeadRepository
){

    suspend fun currentLocation(lat: Double, lng: Double) {
        repository.currentLocation(prefs.userId(), lat, lng)
    }




/*
    suspend fun submitFCMToken(networkStatus: Boolean, fcmToken: String) {
        if (networkStatus) {
            if (prefs.lastFCMToken().isNotEmpty()) {
                repository.submitFCMToken(prefs.lastFCMToken(), prefs.userId())
                prefs.removeLastFCMToken()
            } else {
                repository.submitFCMToken(fcmToken, prefs.userId())
            }
        } else {
            prefs.storeFCMToken(fcmToken)
        }
    }*/





    fun AddLead(
        clientName:String,
        emailId: String,
        phoneNumber:String,
        alternateNumber:String,
        companyName:String,
        website:String,saleValue:String,notes:String,lat: Double,lng: Double
    ) = flow<Data>{
        emit(Data(EmitType.Loading, true))
        when(val response = repository.addNewLead(
            clientName = clientName,
            emailId = emailId,
            phoneNumber = phoneNumber,
            alternateNumber = alternateNumber,
            companyName = companyName,
            website = website,saleValue=saleValue,notes = notes, lat = lat, lng = lng,
            prefs.userId()
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