package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bsquare.core.entities.ActivityDetails
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class BaseViewModel @Inject constructor(
    val appNavigator: AppNavigator,
) : ViewModel(){
    var leadToLeadDetailsArg = mutableStateOf("")

    var changeToLeadDetailsArg =  mutableStateListOf<String>("")




    var refreshLoadDataArg = mutableStateOf(false)

    var addedActivityArg = mutableStateListOf<ActivityDetails?>(null)



}