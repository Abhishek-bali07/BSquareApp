package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bsquare.app.utills.helper_impl.SavableMutableState
import com.bsquare.app.utills.helper_impl.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddLeadViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    val clientName = mutableStateOf("")
    val emailId = mutableStateOf("")
    val phoneNumber = mutableStateOf("")
    val alternateNumber = mutableStateOf("")
    val comName = mutableStateOf("")
    val websiteName = mutableStateOf("")
    val saleValue = mutableStateOf("")
    val notes = mutableStateOf("")



    fun onChangeName(n: String) {
        clientName.value = n
    }

    fun onEmailChange(e:String){
        emailId.value = e
    }

    fun onNumberChange(m:String){
        phoneNumber.value = m
    }

    fun onAlternateChange(altnum: String){
        alternateNumber.value = altnum
    }
    fun onCompanyChange(cn : String){
        comName.value = cn
    }
    fun onWebsiteChange(wc:String){
        websiteName.value = wc
    }
    fun onsaleChange(sv:String){
        saleValue.value = sv
    }

    fun onChangeNotes(nc:String){
        notes.value = nc
    }



    val enableBtn = SavableMutableState(
        key = UiData.AddNewClientBtnEnable,
        savedStateHandle = savedStateHandle,
        initialData = false
    )

    val loginLoading = SavableMutableState(
        key = UiData.NewClientApiLoading,
        savedStateHandle = savedStateHandle,
        initialData = false
    )
}