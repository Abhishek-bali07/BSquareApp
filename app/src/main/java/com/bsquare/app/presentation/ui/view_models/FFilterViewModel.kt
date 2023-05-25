package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.FilterTypeData
import com.bsquare.core.usecases.FollowFilterUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject



@HiltViewModel
class FFilterViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private  val useCase: FollowFilterUseCase
) :ViewModel() {

    private val _filterDetails = MutableStateFlow<FilterTypeData?>(null)
    val filterDetail = _filterDetails.asStateFlow()

    val selectedItem = mutableListOf("")

    val loader = mutableStateOf(false)

    val errorToast = mutableStateOf("")


    fun filterData(){
        useCase.FilterInitialData().onEach {
            when(it.type){
            EmitType.Loading ->{
                it.value.apply {
                    castValueToRequiredTypes<Boolean>()?.let {
                        loader.value = it
                    }
                }
            }

            EmitType.FollowFilterDetails ->{
                it.value?.castValueToRequiredTypes<FilterTypeData>()?.let { data ->
                    _filterDetails.update { data }
                    data.dataLead.map {
                        if (it.isSelected && !selectedItem.contains(it.dataLeadId)){
                            selectedItem.add(it.dataLeadId)
                        }
                    }
                    data.dataLabel.map {
                        if(it.isSelected && !selectedItem.contains(it.labelId)){
                            selectedItem.add(it.labelId)
                        }
                    }

                    data.dataStatus.map {
                        if(it.isSelected && !selectedItem.contains(it.statusId)){
                            selectedItem.add(it.statusId)
                        }
                    }

                    data.dataSource.map {
                        if (it.isSelected && !selectedItem.contains(it.dataSourceId)){
                            selectedItem.add(it.dataSourceId)
                        }
                    }

                    data.teamMember.map {
                        if (it.isSelected && !selectedItem.contains(it.teamMemberId)){
                            selectedItem.add(it.teamMemberId)
                        }
                    }

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

    fun onClickDataLeadChip(selectedIdx: Int) {
        _filterDetails.update { data ->
            data?.dataLead?.map {
                if (selectedIdx == data.dataLead.indexOf(it)) {
                    val seleted = it.copy(isSelected = !it.isSelected)
                    if(seleted.isSelected){
                        selectedItem.add(seleted.dataLeadId)
                    }
                    else{
                        if (selectedItem.contains(seleted.dataLeadId)){
                            selectedItem.remove(seleted.dataLeadId)
                        }
                    }
                    return@map seleted
                }
                it
            }?.toList()?.let { data.copy(dataLead = it) }
        }
    }

}