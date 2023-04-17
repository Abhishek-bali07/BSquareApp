package com.bsquare.app.presentation.ui.view_models

import androidx.lifecycle.ViewModel
import com.bsquare.core.usecases.LeadUseCase
import com.bsquare.core.utils.helper.AppStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeadViewModel @Inject constructor(
  private  val  useCase: LeadUseCase
):ViewModel(){
}