package com.bsquare.core.domain.repositories.intro


import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.AppVersionResponse

interface SplashRepository {
    suspend fun appVersion(currentVersion: Int): Resource<AppVersionResponse>
}