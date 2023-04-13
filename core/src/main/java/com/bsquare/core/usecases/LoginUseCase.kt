package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Resource.Error
import com.bsquare.core.common.constants.Resource.Success
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.common.extras.Data
import com.bsquare.core.domain.repositories.login.LoginRepository
import kotlinx.coroutines.flow.flow
import com.bsquare.core.common.constants.handleFailedResponse
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
                            emit(Data(type = EmitType.Inform, null))
                        }
                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }
            is Error -> {
                emit(Data(EmitType.Loading, false))

            }
            else -> {

            }
        }
    }


}


