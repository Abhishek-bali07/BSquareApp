package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddActivityViewModel @Inject constructor(


):ViewModel() {



    val selectedDate =  mutableStateOf("")
    val selectedTime = mutableStateOf("")
    val selectLeads = mutableStateOf("")
    val activityType = mutableStateOf("")

    val isExpanded = mutableStateOf(false)
}