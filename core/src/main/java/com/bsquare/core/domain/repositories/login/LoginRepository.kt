package com.bsquare.core.domain.repositories.login

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.LoginVerifyResponse

interface LoginRepository {

    suspend fun  login(mobileNumber: String,companyName : String, userPassword: String): Resource<LoginVerifyResponse>
}