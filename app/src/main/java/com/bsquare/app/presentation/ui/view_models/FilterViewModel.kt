package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
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
                    return@map it.copy(isSelected = !it.isSelected)
                }
                it
            }?.toList()?.let { data.copy(dataLead = it) }
        }
    }

}