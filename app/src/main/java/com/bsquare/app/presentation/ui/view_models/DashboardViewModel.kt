package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bsquare.core.entities.ShortDetails
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
) : ViewModel() {
    val shortDetail = mutableStateOf<ShortDetails?>(null)


}