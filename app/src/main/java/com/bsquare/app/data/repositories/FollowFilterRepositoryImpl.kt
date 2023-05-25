package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.followup.FollowupFilterRepository
import com.bsquare.core.entities.responses.FollowFilterResponse
import javax.inject.Inject

class FollowFilterRepositoryImpl @Inject constructor() : FollowupFilterRepository {
    override suspend fun getFilterData(
        userId: String): Resource<FollowFilterResponse> {
        TODO("Not yet implemented")
    }
}