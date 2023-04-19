package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.LeadData
import com.bsquare.core.usecases.DetailsUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CompanyDetailViewModel @Inject constructor(
    private val appNavigator:AppNavigator,
    private val  useCase: DetailsUseCase

): ViewModel(){
    val companyDetails = mutableStateOf<LeadData?>(null)



    fun initialData(leadID: String) {
      useCase.InitialDetails(leadID).onEach {
          when(it.type){
              EmitType.LeadsDetails -> {
                  it.value?.castValueToRequiredTypes<LeadData>()?.let{

                  }
              }
              else -> {}
          }
      }

    }


}