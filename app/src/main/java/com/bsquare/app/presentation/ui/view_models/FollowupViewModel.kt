package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castListToRequiredTypes
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.Follow
import com.bsquare.core.usecases.FollowupUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FollowupViewModel @Inject constructor(
private  val useCase: FollowupUseCase,
private val appNavigator: AppNavigator
): ViewModel() {


    val ftoday = mutableStateListOf<Follow>()
    val fupcoming = mutableStateListOf<Follow>()
    val foverdue = mutableStateListOf<Follow>()
    val fdone = mutableStateListOf<Follow>()


    init {
        getFollowUpData()
    }





    fun getFollowUpData(){
        useCase.initialFollowData().onEach {
            when(it.type){
                EmitType.Loading ->{
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {

                    }
                }
                EmitType.FollowItem ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        ftoday.addAll(it)
                    }

                EmitType.UpcomingFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        fupcoming.addAll(it)
                    }

                EmitType.OverdueFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        foverdue.addAll(it)
                    }

                EmitType.DoneFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        fdone.addAll(it)
                    }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }




    val pages = listOf(
        "Today" ,
        "Upcoming",
        "Overdue",
        " Done"
    )
}