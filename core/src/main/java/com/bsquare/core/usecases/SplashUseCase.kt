package com.bsquare.core.usecases
import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.constants.handleFailedResponse
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.common.enums.IntroStatus

import com.bsquare.core.domain.repositories.intro.SplashRepository
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SplashUseCases @Inject constructor(
    private val splashRepository: SplashRepository,
    private val appStore: AppStore
){
    fun checkIntroStatus() = flow {
        if (!appStore.isIntroDone()) {
            appStore.intro(true)
            emit(Data(type = EmitType.IntroStatus, IntroStatus.NOT_DONE))
        } else {
            emit(Data(type = EmitType.IntroStatus, IntroStatus.DONE))
        }
    }

    fun checkAppVersion() = flow {
        //val currentVersion = appInfo.getCurrentVersion()
        when (val response = splashRepository.appVersion(1)) {
            is Resource.Success -> {
                response.data?.apply {
                    when (status) {
                        true -> {
                            if (1 < appVersion.versionCode) {
                                emit(Data(type = EmitType.AppVersion, value = appVersion))
                            } else {
                                if(appStore.isLoggedIn()) {
                                    emit(
                                        Data(
                                            type = EmitType.Navigate,
                                            value = Destination.LoginScreen
                                        )
                                    )
                                } else {
                                    emit(
                                        Data(
                                            type = EmitType.Navigate,
                                            value = Destination.LoginScreen
                                        )
                                    )
                                }
                            }
                        }
                        else -> {
                            emit(Data(type = EmitType.BackendError, value = message))
                        }
                    }
                }
            }
            is Resource.Error -> {
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


    fun  navigateToAppropiateScreen() = flow<Data> {
        if (appStore.isLoggedIn()){
            emit(Data(type = EmitType.Navigate, value = Destination.LoginScreen))
        }else{
            emit(Data(type = EmitType.Navigate, value = Destination.SplashScreen))
        }
    }
}