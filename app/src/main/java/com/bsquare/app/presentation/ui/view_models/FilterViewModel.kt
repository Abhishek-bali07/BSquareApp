package com.bsquare.app.presentation.ui.view_models

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.DataLead
import com.bsquare.core.entities.FilterTypeData
import com.bsquare.core.usecases.FilterPageUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private val useCase: FilterPageUseCase
) : ViewModel() {

    private val _filterDetails = MutableStateFlow<FilterTypeData?>(null)
    val filterDetails = _filterDetails.asStateFlow()

    val selectedItem = mutableListOf("")

    val selectedDate =  mutableStateOf<DataLead?>(null)

    val loader = mutableStateOf(false)
    val errorToast = mutableStateOf("")

    init {
        filterData()
    }

    fun filterData() {
        useCase.FilterInitialData().onEach {
            when (it.type) {
                EmitType.Loading ->{
                    it.value.apply {
                        castValueToRequiredTypes<Boolean>()?.let {
                            loader.value = it
                        }
                    }
                }
                EmitType.leadFilterDetails -> {
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

    fun onClickDataLabelChip(selectIdx: Int){
        _filterDetails.update { data ->
                data?.dataLabel?.map {
                    if(selectIdx == data.dataLabel.indexOf(it)){
                        val selected = it.copy(isSelected = !it.isSelected)
                        if (selected.isSelected){
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
                    if (select.isSelected){
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
                    if(dsselected.isSelected){
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
                    if (mselected.isSelected){
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


            selectedItem.clear()
            FilterTypeData(
                dataLead = updateDataLead!!,
                dataLabel = updatedData!!,
                dataStatus = updateDataStatus!!,
                dataSource = updateDataSource!!,
                teamMember = updateDataMember!!

                )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun onSelectCustomData(customDate: String) {
        _filterDetails.mapLatest {
            var isFound = false

            it?.dataLead?.forEach {
                if(selectedItem.contains(it.dataLeadId)) isFound = true
            }

            if(!isFound) {
                selectedDate.value = DataLead(
                    dataLeadId = customDate,
                    dataLeadName = customDate,
                    isSelected = true
                )

                selectedItem.add(customDate)
            }

        }.launchIn(viewModelScope)
    }
}