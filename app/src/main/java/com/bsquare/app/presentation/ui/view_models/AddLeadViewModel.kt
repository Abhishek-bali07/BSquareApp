package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.R
import com.bsquare.app.presentation.states.Dialog
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.app.presentation.states.string
import com.bsquare.app.utills.helper_impl.SavableMutableState
import com.bsquare.app.utills.helper_impl.UiData
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.constants.DialogData
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.usecases.AddLeadUseCase
import com.google.android.gms.maps.model.Marker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddLeadViewModel @Inject constructor(

    private  val addLeadUseCase: AddLeadUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    var toastNotify = mutableStateOf("")

    val clientName = mutableStateOf("")
    val emailId = mutableStateOf("")
    val phoneNumber = mutableStateOf("")
    val alternateNumber = mutableStateOf("")
    val comName = mutableStateOf("")
    val websiteName = mutableStateOf("")
    val saleValue = mutableStateOf("")
    val notes = mutableStateOf("")
    val showRationaleDialog = mutableStateOf<Dialog?>(null)

    val allPermissionNotGranted = mutableStateOf<Dialog?>(null)

    val permissionDeniedForever = mutableStateOf<Dialog?>(null)

    val openAppSettings = mutableStateOf<Long?>(null)

    val openEnableGps = mutableStateOf<Dialog?>(null)



    val getMarkers = mutableListOf<Marker>()


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

    val addLoading = SavableMutableState(
        key = UiData.NewClientApiLoading,
        savedStateHandle = savedStateHandle,
        initialData = false
    )




    fun newLead(){
        addLeadUseCase.AddLead(
            clientName = clientName.value,
            emailId = emailId.value,
            phoneNumber = phoneNumber.value,
            alternateNumber = alternateNumber.value,
            companyName = clientName.value,
            website = websiteName.value,
            saleValue = saleValue.value,
            notes = notes.value
        ).onEach{
            when(it.type){
                EmitType.AddNewLead ->{
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {
                      addLoading.setValue(it)
                    }
                }

                EmitType.Navigate ->{
                    it.value?.apply {
                        castValueToRequiredTypes<Destination.NoArgumentsDestination>()?.let {
                            destination ->
                        }
                    }
                }

                EmitType.NetworkError -> {
                    it.value?.apply {
                        castValueToRequiredTypes<String>()?.let {
                            toastNotify.value = it
                        }
                    }
                }
                EmitType.BackendError -> {
                    it.value?.apply {
                        castValueToRequiredTypes<String>()?.let {
                            toastNotify.value = it
                        }
                    }
                }

                else -> {}
            }
        }
    }


    fun onGetCurrentLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
          //  addLeadUseCases.currentLocation(latitude, longitude);
        }
    }



    fun onShowRationale() {
        showRationaleDialog.value = Dialog(
            data = DialogData(
                title = R.string.app_name.string(),
                message = R.string.location_rationale_message.string(),
                positive = R.string.okay.string(),
            )
        )

        showRationaleDialog.value?.onConfirm = {
            showRationaleDialog.value?.setState(Dialog.Companion.State.DISABLE)
        }
    }


    fun onPermissionDeniedForever() {
        permissionDeniedForever.value = Dialog(
            data = DialogData(
                title = R.string.app_name.string(),
                message = R.string.all_permission_not_granted.string(),
                positive = R.string.okay.string()
            )
        )

        permissionDeniedForever.value?.onConfirm = {
            openAppSettings.value = System.currentTimeMillis()
            permissionDeniedForever.value?.setState(Dialog.Companion.State.DISABLE)
        }
    }


    fun onAllPermissionNotGranted() {
        allPermissionNotGranted.value = Dialog(
            data = DialogData(
                title = R.string.app_name.string(),
                message = R.string.exact_permission_not_granted.string(),
                positive = R.string.okay.toString()
            )
        )

        allPermissionNotGranted.value?.onConfirm = {
            openAppSettings.value = System.currentTimeMillis()
            allPermissionNotGranted.value?.setState(Dialog.Companion.State.DISABLE)
        }
    }




}