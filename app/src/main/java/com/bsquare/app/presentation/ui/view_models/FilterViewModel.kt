package com.bsquare.app.presentation.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.FilterTypeData
import com.bsquare.core.usecases.FilterPageUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private val useCase: FilterPageUseCase
) : ViewModel() {

    private val _filterDetails = MutableStateFlow<FilterTypeData?>(null)
    val filterDetails = _filterDetails.asStateFlow()

    val selectedItem = mutableListOf<String>("")

    init {
        filterData()
    }

    fun filterData() {
        useCase.FilterInitialData().onEach {
            when (it.type) {
                EmitType.leadFilterDetails -> {
                    it.value?.castValueToRequiredTypes<FilterTypeData>()?.let { data ->
                        _filterDetails.update { data }
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
                    if(it.isSelected){
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

    fun onClickDataLabelChip(selectIdx: Int){
        _filterDetails.update { data ->
                data?.dataLabel?.map {
                    if(selectIdx == data.dataLabel.indexOf(it)){
                        val selected = it.copy(isSelected = !it.isSelected)
                        if (it.isSelected){
                            selectedItem.add(selected.labelId)
                        }else{
                            if (selectedItem.contains(selected.labelId)){
                                selectedItem.remove(selected.labelId)
                            }
                        }
                        return@map selected
                    }
                    it
                }?.toList()?.let {
                    data.copy(dataLabel = it)
                }
        }
    }

    fun onClickDataStatusChip(selectIdx: Int){
        _filterDetails.update { data ->
            data?.dataStatus?.map {
                if(selectIdx == data.dataStatus.indexOf(it)){
                    val select = it.copy(isSelected = !it.isSelected)
                    if (it.isSelected){
                        selectedItem.add(select.statusId)
                    }else{
                        if (selectedItem.contains(select.statusId)){
                            selectedItem.remove(select.statusId)
                        }
                    }
                    return@map select
                }
                it
            }?.toList()?.let {
                data.copy(dataStatus = it)
            }
        }
    }


    fun onClickDataSourceChip(selectIdx: Int){
        _filterDetails.update { data ->
            data?.dataSource?.map {
                if(selectIdx == data.dataSource.indexOf(it)){
                    val dsselected = it.copy(isSelected = !it.isSelected)
                    if(it.isSelected){
                        selectedItem.add(dsselected.dataSourceId)
                    }else{
                        if (selectedItem.contains(dsselected.dataSourceId)){
                            selectedItem.remove(dsselected.dataSourceId)
                        }
                    }
                    return@map  dsselected
                }
                it
            }?.toList()?.let {
                data.copy(dataSource = it)
            }
        }
    }


    fun onClickTeamMemberChip(selectIdx: Int){
        _filterDetails.update { data ->
            data?.teamMember?.map {
                if(selectIdx == data.teamMember.indexOf(it)){
                    val  mselected = it.copy(isSelected = !it.isSelected)
                    if (it.isSelected){
                        selectedItem.add(mselected.teamMemberId)
                    }else{
                        if (selectedItem.contains(mselected.teamMemberId)){
                            selectedItem.remove(mselected.teamMemberId)
                        }
                    }
                    return@map mselected
                }
                it
            }?.toList()?.let {
                data.copy(teamMember = it)
            }
        }
    }




    fun onResetBtnClick(){
        _filterDetails.update {
            val updatedData = it?.dataLabel?.map {
                it.copy(isSelected = false)
            }

            val updateDataLead = it?.dataLead?.map {
                it.copy(isSelected = false)
            }

            val updateDataStatus = it?.dataStatus?.map {
                it.copy(isSelected = false)
            }

            val updateDataSource = it?.dataSource?.map {
                it.copy(isSelected = false)
            }
            val updateDataMember = it?.teamMember?.map {
                it.copy(isSelected = false)
            }


            it
        }
    }

}