package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.app.utills.helper_impl.SavableMutableState
import com.bsquare.app.utills.helper_impl.UiData
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.usecases.LoginUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: LoginUseCase,
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val companyName = mutableStateOf("")
    val mobile = mutableStateOf("")
    val password = mutableStateOf("")
    var showPassword by mutableStateOf(false)
    var toastNotify = mutableStateOf("")


    fun onChangeName(n: String) {
        companyName.value = n
    }

    fun onChangePassword(p: String) {
        password.value = p
        enableBtn.setValue(derivedStateOf {
            password.value.length >= 8
        }.value)
    }


    val enableBtn = SavableMutableState(
        key = UiData.LoginContinueBtnEnable,
        savedStateHandle = savedStateHandle,
        initialData = false
    )
    val loginLoading = SavableMutableState(
        key = UiData.LoginApiLoading,
        savedStateHandle = savedStateHandle,
        initialData = false
    )


    fun appLogin() {
        useCases.loginData(
            mobileNumber = mobile.value,
            companyName = companyName.value,
            userPassword = password.value
        ).onEach {
            when(it.type){
                EmitType.Loading -> {
                    it.value?.apply {
                        castValueToRequiredTypes<Boolean>()?.let {
                            loginLoading.setValue(it)
                        }
                    }
                }
                EmitType.Navigate -> {
                    it.value?.apply {
                        castValueToRequiredTypes<Destination.NoArgumentsDestination>()?.let {destination ->
                            appNavigator.navigateTo(destination(), )
                        }
                    }
                }
                EmitType.NetworkError -> {
                        it.value?.apply {
                            castValueToRequiredTypes<String>()?.let {
                                toastNotify.value = it
                            }
                        }
                 }
                EmitType.BackendError -> {
                    it.value?.apply {
                        castValueToRequiredTypes<String>()?.let {
                            toastNotify.value = it
                        }
                    }
                }
                else -> {}
            }

        }.launchIn(viewModelScope)
    }
}