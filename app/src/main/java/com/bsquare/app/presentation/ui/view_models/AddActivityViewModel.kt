package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.app.utills.helper_impl.SavableMutableState
import com.bsquare.app.utills.helper_impl.UiData
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.ActivityDetailData
import com.bsquare.core.usecases.AddActivityUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddActivityViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private val useCase:AddActivityUseCase,
    private val savedStateHandle: SavedStateHandle,

    ):ViewModel() {



    val selectedDate =  mutableStateOf("")
    val selectedTime = mutableStateOf("")
    val selectLeads = mutableStateOf("")
    val activityType = mutableStateOf("")

    val isExpanded = mutableStateOf(false)

    val isMenuExpanded = mutableStateOf(false)


    val alterPhn = mutableStateOf("")

    val companyName = mutableStateOf("")

    val activityNote = mutableStateOf("")

    val activitylistDetail = mutableStateOf<ActivityDetailData?>(null)

    fun onChangeAlterPhn(n: String) {
        alterPhn.value = n
    }


    fun onChangeCompanyName(m: String) {
        companyName.value = m
    }

    val enableBtn = SavableMutableState(
        key = UiData.AddActivityBtnEnable,
        savedStateHandle = savedStateHandle,
        initialData = false
    )


    val addLoading = SavableMutableState(
        key = UiData.AddActivityLoading,
        savedStateHandle = savedStateHandle,
        initialData = false
    )

    init {
        initialData()
    }


    fun  initialData(){
        useCase.InitialDetails().onEach {
            when(it.type){
                EmitType.AddActivityDetails ->{
                    it.value?.castValueToRequiredTypes<ActivityDetailData>()?.let {
                        activitylistDetail.value = it
                    }
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

}