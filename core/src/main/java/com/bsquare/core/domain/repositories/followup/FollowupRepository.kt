package com.bsquare.core.domain.repositories.followup

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.entities.responses.FollowupResponse

interface FollowupRepository {

    suspend fun  initialData(userId: String) :Resource<FollowupResponse>
}