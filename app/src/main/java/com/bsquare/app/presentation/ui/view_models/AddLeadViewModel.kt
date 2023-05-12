package com.bsquare.app.presentation.ui.view_models

import android.util.Log
import android.util.Patterns
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.bsquare.core.utils.helper.AppNavigator
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddLeadViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val addLeadUseCase: AddLeadUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val addedLatLng = mutableStateOf<LatLng?>(null)

    var toastNotify = mutableStateOf("")

    val clientName = mutableStateOf("")
    val emailId = mutableStateOf("")
    val phoneNumber = mutableStateOf("")
    val alternateNumber = mutableStateOf("")
    val comName = mutableStateOf("")
    val websiteName = mutableStateOf("")
    val saleValue = mutableStateOf("")
    val notes = mutableStateOf("")

    val isEmailError = mutableStateOf(false)
    val isNumberError = mutableStateOf(false)
    val isWebsiteError = mutableStateOf(false)

    val showRationaleDialog = mutableStateOf<Dialog?>(null)

    val allPermissionNotGranted = mutableStateOf<Dialog?>(null)

    val permissionDeniedForever = mutableStateOf<Dialog?>(null)

    val openAppSettings = mutableStateOf<Long?>(null)

    val openEnableGps = mutableStateOf<Dialog?>(null)





    init {
        validateInputs()
    }



    fun onChangeName(n: String) {
        clientName.value = n
    }

    fun onEmailChange(e: String) {
        emailId.value = e
        savedStateHandle[UiData.e.toString()] = e
        isEmailError.value = derivedStateOf {
            if (e.isEmpty()) return@derivedStateOf false
            if (e.matches(Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+\$"))) return@derivedStateOf false
            true
        }.value
    }

    fun onNumberChange(m: String) {
        phoneNumber.value = m


        /*savedStateHandle[UiData.m.toString()] = m
        isNumberError.value = derivedStateOf {
            if(m.isEmpty()) return@derivedStateOf false
            if (m.matches(Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+\$"))) return@derivedStateOf false
            true
        }.value*/
    }

    fun onAlternateChange(altnum: String) {
        alternateNumber.value = altnum
    }

    fun onCompanyChange(cn: String) {
        comName.value = cn
    }

    fun onWebsiteChange(wc: String) {
        websiteName.value = wc
        savedStateHandle[UiData.wc.toString()] = UiData.wc
        isWebsiteError.value = derivedStateOf {
            if(wc.isEmpty()) return@derivedStateOf false
            if (wc.matches(Regex(" “((http|https)://)(www.)?” \n" +
                        "+ “[a-zA-Z0-9@:%._\\\\+~#?&//=]{2,256}\\\\.[a-z]” \n" +
                        "+ “{2,6}\\\\b([-a-zA-Z0-9@:%._\\\\+~#?&//=]*)”"))) return@derivedStateOf false
            true
        }.value

    }

    fun onsaleChange(sv: String) {
        saleValue.value = sv
    }

    fun onChangeNotes(nc: String) {
        notes.value = nc
    }


    val drawerGuestureState = SavableMutableState(
        key = UiData.DrawerGuestureState,
        savedStateHandle = savedStateHandle,
        initialData = false
    )

    val scaffoldState = ScaffoldState(
        drawerState = DrawerState(initialValue = DrawerValue.Closed),
        snackbarHostState = SnackbarHostState()
    )


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

    fun submitFcmToken(token: String) {
        viewModelScope.launch {

        }
    }


    fun newLead() {
        addedLatLng.value?.let {
            addLeadUseCase.AddLead(
                clientName = clientName.value,
                emailId = emailId.value,
                phoneNumber = phoneNumber.value,
                alternateNumber = alternateNumber.value,
                companyName = clientName.value,
                website = websiteName.value,
                saleValue = saleValue.value,
                notes = notes.value,
                lat =it.latitude,
                lng = it.longitude
            ).onEach {
                when (it.type) {
                    EmitType.Loading -> {
                        it.value?.apply {
                            castValueToRequiredTypes<Boolean>()?.let {
                                addLoading.setValue(it)
                            }
                            }
                        }

                    EmitType.Navigate -> {
                        it.value?.apply {
                            castValueToRequiredTypes<Destination>()?.let { destination ->
                                appNavigator.tryNavigateBack()
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
            }.launchIn(viewModelScope)
        }
    }


    fun onGetCurrentLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            addedLatLng.value = LatLng(latitude, longitude)
            addLeadUseCase.currentLocation(latitude, longitude)
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

    fun enableGpsDialog() {
        openEnableGps.value = Dialog(
            data = DialogData(
                title = R.string.app_name.string(),
                message = R.string.please_enable_your_gps.string(),
                positive = R.string.okay.string(),
                negative = R.string.no.toString()
            )
        )
        openEnableGps.value?.onConfirm = {
            openAppSettings.value = System.currentTimeMillis()
            openEnableGps.value?.setState(Dialog.Companion.State.DISABLE)
        }
        openEnableGps.value?.onDismiss = {
            openEnableGps.value?.setState(Dialog.Companion.State.DISABLE)
        }
    }

    private fun validateInputs() {
        viewModelScope.launch {
            while(true) {
                delay(200L)
                enableBtn.setValue(
                    when {
                    clientName.value.isEmpty() ->{ false }
                    emailId.value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailId.value).matches() -> {false}
                    phoneNumber.value.isEmpty() || !Patterns.PHONE.matcher(phoneNumber.value).matches() -> {false}
                    websiteName.value.isEmpty() || !Patterns.WEB_URL.matcher(websiteName.value).matches() -> {false}
                    alternateNumber.value.isEmpty() ->{false}
                    comName.value.isEmpty() ->{false}
                    saleValue.value.isEmpty() ->{false}
                    notes.value.isEmpty() ->{false}


                    else -> true
                })
            }
        }
    }
}