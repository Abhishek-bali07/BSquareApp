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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FollowupViewModel @Inject constructor(
private  val useCase: FollowupUseCase,
private val appNavigator: AppNavigator
): ViewModel() {


    val isTextfieldExpanded = mutableStateOf(false)

    val selectPager = mutableStateOf<SearchPageTab?>(SearchPageTab())

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
    }

    fun onChangeUpcomingTxt(u :String){
        usearchTxt.value = u
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
                        ftoday.clear()
                        ftoday.addAll(it)
                    }

                EmitType.UpcomingFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        fupcoming.clear()
                        fupcoming.addAll(it)
                    }

                EmitType.OverdueFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
                        foverdue.clear()
                        foverdue.addAll(it)
                    }

                EmitType.DoneFollow ->
                    it.value?.castListToRequiredTypes<Follow>()?.let {
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