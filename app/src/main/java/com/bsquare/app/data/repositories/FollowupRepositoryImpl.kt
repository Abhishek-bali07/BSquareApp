package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.followup.FollowupRepository
import com.bsquare.core.entities.Follow
import com.bsquare.core.entities.responses.FollowupResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class FollowupRepositoryImpl @Inject constructor() : FollowupRepository{
    override suspend fun initialData(
        userId: String): Resource<FollowupResponse> {
        delay(2000L)
        return  Resource.Success(
            FollowupResponse(
                status = true,
                message = "Success",
                today = MutableList(4){
                    idx -> Follow(
                    id = "ID_${idx+1}",
                    companyName = "INFOSYS",
                    ownerName = "Ricky",
                    time = "12:00PM",
                    companyIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                    )
                },
                upcoming = MutableList(3){
                        idx -> Follow(
                    id = "ID_${idx+1}",
                    companyName = "INFOSYS",
                    ownerName = "Ricky",
                    time = "12:00PM",
                    companyIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                )
             },
                overdue =  MutableList(2){
                        idx -> Follow(
                    id = "ID_${idx+1}",
                    companyName = "INFOSYS",
                    ownerName = "Ricky",
                    time = "12:00PM",
                    companyIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                )
             },
                done =  MutableList(1){
                        idx -> Follow(
                    id = "ID_${idx+1}",
                    companyName = "INFOSYS",
                    ownerName = "Ricky",
                    time = "12:00PM",
                    companyIconUrl = "https://www.v-xplore.com/dev/rohan/assets/meeting.svg",
                )
                },
            )
        )
    }
}