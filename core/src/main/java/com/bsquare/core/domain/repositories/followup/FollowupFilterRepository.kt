package com.bsquare.core.domain.repositories.followup

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.FollowFilterResponse

interface FollowupFilterRepository {

    suspend fun getFilterData(userId: String) : Resource<FollowFilterResponse>
}