package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.app.utills.helper_impl.SavableMutableState
import com.bsquare.app.utills.helper_impl.UiData
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.TaskDetailData
import com.bsquare.core.usecases.AddTaskUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class AddTaskViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private val useCase: AddTaskUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel(){

    val tasklistDetails = mutableStateOf<TaskDetailData?>(null)

    val selectedDate =  mutableStateOf("")
    val selectedTime = mutableStateOf("")
    val selectLeads = mutableStateOf("")
    val  taskType = mutableStateOf("")
    val taskAssign = mutableStateOf("")
    val repeatVal = mutableStateOf("")
    val taskDesc = mutableStateOf("")
    val dueDate = mutableStateOf("")

    val taskTime = mutableStateOf("")

    var toastNotify = mutableStateOf("")


    val isMenuExpanded = mutableStateOf(false)

    val isExpanded = mutableStateOf(false)

    val isAssignExpanded = mutableStateOf(false)


    fun onChangeRepeatVal(n: String) {
        repeatVal.value = n
    }

    fun onChangeDescription(des: String) {
        taskDesc.value = des
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

    init {
        initialData()
    }





    fun initialData(){
        useCase.InitialDetails().onEach {
            when(it.type){
                EmitType.TaskDetailData ->{
                    it.value?.castValueToRequiredTypes<TaskDetailData>()?.let {
                        tasklistDetails.value = it
                    }
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }


    fun newTask(){
        useCase.addTask(
            taskFor = selectLeads.value,
            taskType = taskType.value,
            dueDate = dueDate.value,
            customDate = selectedDate.value,
            taskTime = selectedTime.value,
            taskRepeat = repeatVal.value,
            taskAssign= taskAssign.value,
            taskDescription = taskDesc.value
        ).onEach {
            when(it.type){

                EmitType.Loading -> {
                    it.value?.apply {
                        castValueToRequiredTypes<Boolean>()?.let {
                            addLoading.setValue(it)
                        }
                    }
                }
                EmitType.Navigate -> {
                    it.value?.apply {
                        castValueToRequiredTypes<Destination>()?.let { destination ->
                            appNavigator.tryNavigateBack()
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
        }
    }

}

