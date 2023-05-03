package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bsquare.app.utills.helper_impl.SavableMutableState
import com.bsquare.app.utills.helper_impl.UiData
import com.bsquare.core.usecases.AddTaskUseCase
import com.bsquare.core.utils.helper.AppNavigator
import javax.inject.Inject

class AddTaskViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private val useCase: AddTaskUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel(){


    val selectLeads = mutableStateOf("")
    val  taskType = mutableStateOf("")
    val repeatVal = mutableStateOf("")

    val isMenuExpanded = mutableStateOf(false)

    val isExpanded = mutableStateOf(false)



    fun onChangeRepeatVal(n: String) {
        repeatVal.value = n
    }



    val enableBtn = SavableMutableState(
        key = UiData.AddNewClientBtnEnable,
        savedStateHandle = savedStateHandle,
        initialData = false
    )


    val addLoading = SavableMutableState(
        key = UiData.NewClientApiLoading,
        savedStateHandle = savedStateHandle,
        initialData = false
    )


}