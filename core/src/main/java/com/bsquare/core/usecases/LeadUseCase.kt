package com.bsquare.core.usecases

import com.bsquare.core.utils.helper.AppStore
import javax.inject.Inject

class LeadUseCase @Inject  constructor(
    private  val prefs:AppStore
){
}