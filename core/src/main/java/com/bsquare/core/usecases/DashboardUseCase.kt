package com.bsquare.core.usecases

import com.bsquare.core.common.constants.Data
import com.bsquare.core.common.constants.Resource
import com.bsquare.core.common.enums.EmitType
import com.bsquare.core.domain.repositories.dashboard.DashboardRepository
import com.bsquare.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val prefs: AppStore, private val repository: DashboardRepository
) {
    fun FeatureData() = flow<Data> {
        emit(Data(EmitType.Loading, value = true))
        when (val response = repository.seefeatures(prefs.userId())) {
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.FeaturesItem, value = this.feature))
                        }
                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }
            is Resource.Error -> {
                emit(Data(EmitType.Loading, false))
            }
            else -> {}
        }
    }
}