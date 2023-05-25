package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.followup.FollowupFilterRepository
import com.bsquare.core.utils.handleFailedResponse
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowFilterUseCase @Inject constructor(
    private val prefs: AppStore,
    private val repository: FollowupFilterRepository
){


    fun FilterInitialData() = flow<Data>{
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getFilterData(prefs.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(type = EmitType.FollowFilterDetails,  value = details))
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