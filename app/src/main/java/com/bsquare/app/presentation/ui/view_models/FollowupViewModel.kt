package com.bsquare.app.presentation.ui.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsquare.app.presentation.states.castListToRequiredTypes
import com.bsquare.app.presentation.states.castValueToRequiredTypes
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.entities.Follow
import com.bsquare.core.entities.SearchPageTab
import com.bsquare.core.usecases.FollowupUseCase
import com.bsquare.core.utils.helper.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowupViewModel @Inject constructor(
private  val useCase: FollowupUseCase,
private val appNavigator: AppNavigator
): ViewModel() {


    val isTextfieldExpanded = mutableStateOf(false)

    val selectPager = mutableStateOf<SearchPageTab?>(SearchPageTab())

    val ftodayForOeration = mutableListOf<Follow>()

    val fupcomingOperation = mutableListOf<Follow>()

    val foverdueOperation = mutableListOf<Follow>()

    val fdoneOperation = mutableListOf<Follow>()



    val ftoday = mutableStateListOf<Follow>()
    val fupcoming = mutableStateListOf<Follow>()
    val foverdue = mutableStateListOf<Follow>()
    val fdone = mutableStateListOf<Follow>()


    val searchTxt  = mutableStateOf<String>("")

    val usearchTxt = mutableStateOf<String>("")

    val overdueTxt = mutableStateOf<String>("")

    val doneTxt = mutableStateOf<String>("")



    fun onChangeSearchTxt(s : String){
        searchTxt.value = s
        viewModelScope.launch {
            delay(500L)
            ftodayForOeration.filter {
                it.companyName.lowercase().contains(s.lowercase())
            }.let {
              ftoday.clear()
              ftoday.addAll(it)
            }
        }
    }

    fun onChangeUpcomingTxt(u :String){
        usearchTxt.value = u
        viewModelScope.launch {
            delay(500L)
            fupcomingOperation.filter {
                it.companyName.lowercase().contains(u.lowercase())
            }.let{
                fupcoming.clear()
                fupcoming.addAll(it)
            }
        }
    }


    fun  onChangeOverdueTxt(o :String){
        overdueTxt.value  = o
    }


    fun onChangeDoneTxt(d : String){
        doneTxt.value = d
    }





    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        getFollowUpData()
    }





    fun getFollowUpData(){
        useCase.initialFollowData().onEach {
            when(it.type){
                EmitType.Loading ->{
                    it.value?.castValueToRequiredTypes<Boolean>()?.let {
                            _isRefreshing.value = it
                    }
                }
                EmitType.FollowItem ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        ftodayForOeration.clear()
                        ftodayForOeration.addAll(it)
                        ftoday.clear()
                        ftoday.addAll(it)
                    }

                EmitType.UpcomingFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        fupcomingOperation.clear()
                        fupcomingOperation.addAll(it)
                        fupcoming.clear()
                        fupcoming.addAll(it)
                    }

                EmitType.OverdueFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        foverdueOperation.clear()
                        fupcomingOperation.addAll(it)
                        foverdue.clear()
                        foverdue.addAll(it)
                    }

                EmitType.DoneFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        fdoneOperation.clear()
                        fdoneOperation.addAll(it)
                        fdone.clear()
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


    fun onBtnClicked(){
        appNavigator.tryNavigateTo(
            Destination.AddTaskScreen(),
            popUpToRoute = null,
            inclusive = false,
            isSingleTop = false
        )
    }



    fun onFilterClicked(){
        appNavigator.tryNavigateTo(
            Destination.FollowFilterScreen(),
            popUpToRoute = null,
            inclusive = false,
            isSingleTop = true
        )
    }
}