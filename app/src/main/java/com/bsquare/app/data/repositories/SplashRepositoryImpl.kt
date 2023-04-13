package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.intro.SplashRepository
import com.bsquare.core.entities.responses.AppVersionResponse
import com.bsquare.core.entities.AppVersion
import kotlinx.coroutines.delay
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(): SplashRepository {

    override suspend fun appVersion(currentVersion: Int): Resource<AppVersionResponse> {
        delay(2000L)
        return  Resource.Success(
            AppVersionResponse(
                status = true,
                message = "Success",
                appVersion = AppVersion(
                    versionCode = 1,
                    releaseDate = "11/3/2018",
                    versionMessage = "This version is outdated",
                    isSkipable = false,
                    link = "asdsad"
                )
            )
        )
    }
}