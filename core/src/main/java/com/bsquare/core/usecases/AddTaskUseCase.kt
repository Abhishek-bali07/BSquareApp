package com.bsquare.core.usecases

import com.bsquare.core.domain.repositories.followup.AddTaskRepository
import com.bsquare.core.utils.helper.AppStore
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private  val prefs: AppStore,
    private val repository:AddTaskRepository
){
}