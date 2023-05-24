package com.bsquare.app.presentation.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.constants.Destination
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
    val appNavigator:AppNavigator,
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

    fun onAddActivity(){
        appNavigator.tryNavigateTo(
            Destination.AddActivityScreen(),
            popUpToRoute = null,
            inclusive = false,
            isSingleTop = true

        )
    }


/*    fun addActivity(writenActivity: String, tabId: String) {
        this.writenActivity.value = ""
        useCase.addDetails(leadIDArg, text = writenActivity, tabId =  tabId).onEach {
            when(it.type){
                EmitType.Loading ->{
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {

                    }
                }
                EmitType.LeadAdded ->{
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {
                        addedActivities.add(writenActivity)
                    }
                }
                else -> {

                }
            }

        }.launchIn(viewModelScope)

    }*/

   /* fun removeActivity(item: String, tabId: String) {
       try {
            useCase.removeDetails(leadIDArg, text = item, tabId = tabId).onEach {
                when(it.type){
                    EmitType.Loading -> {
                        it.value?.castValueToRequiredTypes<Boolean>()?.let {


                        }
                    }

                    EmitType.LeadRemoved -> {
                        it.value?.castValueToRequiredTypes<Boolean>()?.let {
                            addedActivities.remove(item)
                        }
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)

        } catch (ex: Exception) {
            Log.d("TESTING", "EXP ${ex.message}")
        }

    }*/



    fun addTask(writenTask: String, tabId: String) {
        this.writenTask.value = ""
        useCase.addDetails(leadIDArg, text = writenTask, tabId = tabId).onEach {
            when(it.type) {
                EmitType.Loading -> {
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {

                    }
                }
                EmitType.LeadAdded -> {
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {
                        addedTask.add(writenTask)
                    }
                }

                else -> {

                }
            }
        }.launchIn(viewModelScope)

    }

    fun removeTask(wrtitenItem: String, tabId: String) {
        try {
             useCase.removeDetails(leadIDArg, text = wrtitenItem, tabId = tabId).onEach {
                 when(it.type){
                     EmitType.Loading -> {
                         it.value?.castValueToRequiredTypes<Boolean>()?.let {

                         }
                     }
                     EmitType.LeadRemoved -> {
                         it.value?.castValueToRequiredTypes<Boolean>()?.let {
                             addedTask.remove(wrtitenItem)
                         }
                     }


                     else -> {}
                 }
             }.launchIn(viewModelScope)
        }  catch (ex: Exception) {
            Log.d("TESTING", "EXP ${ex.message}")
        }

    }



    fun addInfo(writenInfo: String, tabId: String) {
        this.writenInfo.value = ""
        useCase.addDetails(leadIDArg, text = writenInfo, tabId =  tabId ).onEach {

            when(it.type) {
                EmitType.Loading -> {
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {

                    }
                }
                EmitType.LeadAdded -> {
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {
                        addedInfo.add(writenInfo)
                    }
                }

                else -> {

                }
            }

        }.launchIn(viewModelScope)

    }

    fun removeInfo(wrtitenItem: String, tabId: String) {
        try {

            useCase.removeDetails(leadIDArg, text = wrtitenItem, tabId = tabId).onEach {
                when(it.type){
                    EmitType.Loading ->{

                    }
                    EmitType.LeadRemoved ->{
                        it.value?.castValueToRequiredTypes<Boolean>()?.let {
                            addedInfo.remove(wrtitenItem)
                        }
                    }
                    else -> {

                    }
                }
            }.launchIn(viewModelScope)

        } catch (ex: Exception) {
            Log.d("TESTING", "EXP ${ex.message}")
        }

    }


    fun addNote(writenNote: String, tabId: String) {
        this.writenNote.value = ""
        useCase.addDetails(leadIDArg, text = writenNote, tabId = tabId,).onEach {

            when(it.type) {
                EmitType.Loading -> {
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {

                    }
                }
                EmitType.LeadAdded -> {
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {
                        addedNote.add(writenNote)
                    }
                }

                else -> {

                }
            }
        }.launchIn(viewModelScope)

    }

    fun removeNote(wrtitenItem: String, tabId: String) {
        try {
            useCase.removeDetails(leadIDArg, text = wrtitenItem, tabId).onEach {
                when(it.type){
                    EmitType.Loading ->{

                    }
                    EmitType.LeadRemoved -> {
                        it.value?.castValueToRequiredTypes<Boolean>()?.let {
                        addedNote.remove(wrtitenItem)
                        }
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)

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
                      addedActivities.addAll(it.activity)
                      addedTask.addAll(it.task)
                      addedInfo.addAll(it.info)
                      addedNote.addAll(it.note)
                  }
              }
              else -> {}
          }
      }.launchIn(viewModelScope)

    }


}