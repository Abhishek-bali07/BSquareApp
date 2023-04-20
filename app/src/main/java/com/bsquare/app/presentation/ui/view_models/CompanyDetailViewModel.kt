package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.Label
import com.bsquare.core.entities.LeadDetailsData
import com.bsquare.core.entities.Status
import com.bsquare.core.usecases.DetailsUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CompanyDetailViewModel @Inject constructor(
    private val appNavigator:AppNavigator,
    private val  useCase: DetailsUseCase

): ViewModel(){
    val companyDetails = mutableStateOf<LeadDetailsData?>(null)

    val sExpanded = mutableListOf(false)


    val isMenuExpanded = mutableStateOf(false)

    val isExpanded = mutableStateOf(false)

    val mSelectedText = mutableStateOf("")

    val lSelectedText = mutableStateOf("")






    fun initialData(leadID: String) {
      useCase.InitialDetails(leadID).onEach {
          when(it.type){
              EmitType.LeadsDetails -> {
                  it.value?.castValueToRequiredTypes<LeadDetailsData>()?.let{
                    companyDetails.value = it
                      mSelectedText.value=it.leadLabel.first().labelName
                      lSelectedText.value=it.leadStatus.first().statusName
                  }
              }
              else -> {}
          }
      }.launchIn(viewModelScope)

    }


}