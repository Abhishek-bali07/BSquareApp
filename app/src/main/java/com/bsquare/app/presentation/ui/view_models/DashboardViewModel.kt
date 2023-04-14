package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castListToRequiredTypes
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.Feature
import com.bsquare.core.entities.ShortDetails
import com.bsquare.core.usecases.DashboardUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCase: DashboardUseCase,
    private val appNavigator: AppNavigator,
) : ViewModel() {


    val shortDetail = mutableStateOf<ShortDetails?>(null)
    val features = mutableStateListOf<Feature>()
    init {
        getFeatureData()
    }


    fun getFeatureData() {
        useCase.FeatureData().onEach {
            when (it.type) {
                EmitType.FeaturesItem -> {
                    it.value?.castListToRequiredTypes<Feature>()?.let {
                        features.addAll(it)
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }


}