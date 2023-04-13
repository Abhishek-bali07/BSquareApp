package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.constants.Resource.Error
import com.bsquare.core.common.constants.Resource.Success
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.login.LoginRepository
import com.bsquare.core.utils.handleFailedResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    fun loginData(mobileNumber: String, companyName: String, userPassword: String) = flow<Data> {
        emit(Data(EmitType.Loading, true))
        when (val response = repository.login(mobileNumber, companyName, userPassword)) {
            is Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.Inform, message))
                            emit(Data(type = EmitType.Navigate, Destination.DashboardScreen))
                        }
                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }
            is Error -> {
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


