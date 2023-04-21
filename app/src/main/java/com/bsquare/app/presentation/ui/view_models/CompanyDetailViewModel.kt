package com.bsquare.app.presentation.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.LeadDetailsData
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

    val writenActivity = mutableStateOf("")
    val writenTask = mutableStateOf("")
    val writenInfo = mutableStateOf("")
    val writenNote = mutableStateOf("")

    val addedActivities = mutableStateListOf<String>()
    val addedTask = mutableStateListOf<String>()
    val addedNote = mutableStateListOf<String>()
    val addedInfo = mutableStateListOf<String>()

    fun onChangeNote(n: String) {
        writenNote.value = n
    }

    fun onChangeInfo(i: String) {
        writenInfo.value = i
    }

    fun onChangeTask(t: String) {
        writenTask.value = t
    }

    fun onChangeActivity(a: String) {
        writenActivity.value = a
    }

    fun addActivity(writenActivity: String, tabId: String) {
        this.writenActivity.value = ""
        useCase.addDetails(leadIDArg, text = writenActivity, tabId = )
        addedActivities.add(writenActivity)
    }

    fun removeActivity(wrtitenItem: String) {
        try {
            addedActivities.remove(wrtitenItem)
        } catch (ex: Exception) {
            Log.d("TESTING", "EXP ${ex.message}")
        }

    }



    fun addTask(writenTask: String) {
        this.writenTask.value = ""
        addedTask.add(writenTask)
    }

    fun removeTask(wrtitenItem: String) {
        try {
            addedTask.remove(wrtitenItem)
        } catch (ex: Exception) {
            Log.d("TESTING", "EXP ${ex.message}")
        }

    }



    fun addInfo(writenInfo: String) {
        this.writenInfo.value = ""
        addedInfo.add(writenInfo)
    }

    fun removeInfo(wrtitenItem: String) {
        try {
            addedInfo.remove(wrtitenItem)
        } catch (ex: Exception) {
            Log.d("TESTING", "EXP ${ex.message}")
        }

    }


    fun addNote(writenNote: String) {
        this.writenNote.value = ""
        addedNote.add(writenNote)
    }

    fun removeNote(wrtitenItem: String) {
        try {
            addedNote.remove(wrtitenItem)
        } catch (ex: Exception) {
            Log.d("TESTING", "EXP ${ex.message}")
        }

    }



    val pages = listOf(
        "Add Activity",
        "Add Task",
        "Add Note",
        "Add Info"
    )




    private var leadIDArg = ""

    fun initialData(leadID: String) {
      leadIDArg = leadID
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