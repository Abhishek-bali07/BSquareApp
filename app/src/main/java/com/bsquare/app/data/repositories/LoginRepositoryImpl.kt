package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.login.LoginRepository
import com.bsquare.core.entities.responses.LoginVerifyResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun login(
        mobileNumber: String,
        companyName: String,
        userPassword: String
    ): Resource<LoginVerifyResponse> {
        delay(2000L)
        return Resource.Success(
            LoginVerifyResponse(
                status = true,
                message = "Success"
            )
        )
    }
}