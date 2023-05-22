package com.bsquare.app.presentation.ui.view_models

import android.app.Activity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.app.utills.helper_impl.SavableMutableState
import com.bsquare.app.utills.helper_impl.UiData
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.ActivityDetailData
import com.bsquare.core.entities.ActivityNotesDetails
import com.bsquare.core.usecases.AddActivityUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
    val alterPhn = mutableStateOf("")
    val companyName = mutableStateOf("")
    val activityNote = mutableStateOf("")

    val isExpanded = mutableStateOf(false)

    val isMenuExpanded = mutableStateOf(false)


    val activitylistDetail = mutableStateOf<ActivityDetailData?>(null)


    private  val _noteDetails = MutableStateFlow<ActivityNotesDetails?>(null)
    val noteDetails = _noteDetails.asStateFlow()

    val errorToast = mutableStateOf("")
    var toastNotify = mutableStateOf("")

    val selectedItem = mutableListOf("")

    fun onChangeAlterPhn(n: String) {
        alterPhn.value = n
    }


    fun onChangeCompanyName(m: String) {
        companyName.value = m
    }

    fun onChangeNote(cn: String) {
       activityNote.value = cn
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
        validateInputs()
    }

    private fun validateInputs() {
        viewModelScope.launch {
            while (true){
                delay(200L)
                enableBtn.setValue(
                    when{
                        selectLeads.value.isEmpty() ->{false}
                        activityType.value.isEmpty() ->{false}
                        activityNote.value.isEmpty() ->{false}
                        selectedDate.value.isEmpty() ->{false}
                        companyName.value.isEmpty() -> {false}
                        alterPhn.value.isEmpty() -> {false}
                        selectedTime.value.isEmpty() -> {false}
                        else -> true
                    }
                )
            }
        }
    }


    private fun  initialData(){
        useCase.InitialDetails().onEach {
            when(it.type){
                EmitType.AddActivityDetails ->{
                    it.value?.castValueToRequiredTypes<ActivityDetailData>()?.let {
                        activitylistDetail.value = it
                    }
                }
                EmitType.AddNotesDetails ->{
                    it.value?.castValueToRequiredTypes<ActivityNotesDetails>()?.let { data ->
                        _noteDetails.update { data }
                    }
                }
                EmitType.BackendError -> {
                    it.value?.castValueToRequiredTypes<String>()?.let {
                        errorToast.value = it
                    }
                }
                EmitType.NetworkError -> {
                    it.value?.castValueToRequiredTypes<String>()?.let {
                        errorToast.value = it
                    }
                }

                else -> {}
            }
        }.launchIn(viewModelScope)

    }





    fun onClickDataChip(selectedIdx: Int){
        _noteDetails.update { data ->
            data?.notes?.map {
                if (selectedIdx == data.notes.indexOf(it)){
                    val  selected = it.copy(isSelected = !it.isSelected)
                    if (selected.isSelected){

                        selectedItem.add(selected.notesId)
                    }else{
                        if (selectedItem.contains(selected.notesId)){
                            selectedItem.remove(selected.notesId)
                        }
                    }
                    return@map selected
                }
                it
            }?.toList()?.let { data.copy(notes = it) }

        }

    }



    fun newActivity(){
        useCase.addActivity(
            activityFor = selectLeads.value,
            activityNotes= activityNote.value,
            activityType = activityType.value,
            activityDate = selectedDate.value,
            companyNames = companyName.value,
            activityTime = selectedTime.value,
            phoneNumber = alterPhn.value
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