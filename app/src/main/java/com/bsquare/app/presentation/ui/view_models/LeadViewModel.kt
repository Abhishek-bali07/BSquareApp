package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castListToRequiredTypes
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.Feature
import com.bsquare.core.entities.Leads
import com.bsquare.core.usecases.LeadUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LeadViewModel @Inject constructor(
    private val useCase: LeadUseCase,
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val featureId = savedStateHandle.get<String>(Destination.LeadScreen.featureId_KEY)
    val leads = mutableStateListOf<Leads>()


    init {
        getLeadsData()
    }

    fun getLeadsData() {
        useCase.initialLeads().onEach {
            when (it.type) {
                EmitType.LeadsItem ->
                    it.value?.castListToRequiredTypes<Leads>()?.let {
                        leads.addAll(it)
                    }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }


    fun onCardClicked() {
        featureId?.let {
            appNavigator.tryNavigateTo(
                Destination.CompanyDetailScreen(),
                popUpToRoute = Destination.LeadScreen(featureId = featureId),
                inclusive = false,
                isSingleTop = true
            )
        }

    }


   fun onFilterClicked(){
        appNavigator.tryNavigateTo(
            Destination.FilterScreen(),
            popUpToRoute = null,
            inclusive = false,
            isSingleTop = true
        )
    }


    fun onAddLeads(){
        appNavigator.tryNavigateTo(
            Destination.AddleadScreen(),
            popUpToRoute = null,
            inclusive = false,
            isSingleTop = true
        )
    }


}