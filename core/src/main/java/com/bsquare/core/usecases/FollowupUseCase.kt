package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.followup.FollowupRepository
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowupUseCase @Inject constructor(
    private  val  prefs: AppStore,
    private val  repository:FollowupRepository
) {
    fun initialFollowData() = flow<Data> {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.initialData(prefs.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                       true -> {
                           emit(Data(type = EmitType.FollowItem, value = this.today))
                           emit(Data(type = EmitType.UpcomingFollow, value = this.upcoming))
                           emit(Data(type = EmitType.OverdueFollow, value = this.overdue))
                           emit(Data(type = EmitType.DoneFollow, value = this.done))
                        }

                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }

            else -> {}
        }
    }
}