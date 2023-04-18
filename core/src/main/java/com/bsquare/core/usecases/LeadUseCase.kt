package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.lead.LeadRepository
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeadUseCase @Inject  constructor(
    private  val prefs : AppStore,
    private val repository: LeadRepository
){


    fun initialLeads() = flow<Data> {
     emit(Data(EmitType.Loading, value = true))
    when(val  response = repository.initialLeadData(prefs.userId())){
        is  Resource.Success -> {
            emit(Data(EmitType.Loading, false))
            response.data?.apply {
                when(status){
                    true -> {
                        emit(Data(type = EmitType.LeadsItem, value = this.leads))
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