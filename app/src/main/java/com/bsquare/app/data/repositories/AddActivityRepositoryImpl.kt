package com.bsquare.app.data.repositories

import com.bsquare.core.common.constants.Resource
import com.bsquare.core.domain.repositories.followup.AddTaskRepository
import com.bsquare.core.domain.repositories.lead.AddActivityRepository
import com.bsquare.core.entities.ActivityDetailData
import com.bsquare.core.entities.ActivityLeads
import com.bsquare.core.entities.ActivityType
import com.bsquare.core.entities.NotesData
import com.bsquare.core.entities.responses.ActivityListResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class AddActivityRepositoryImpl @Inject constructor() : AddActivityRepository{
    override suspend fun getInitialData(
        userId: String
    ): Resource<ActivityListResponse> {
        delay(2000L)
        return  Resource.Success(
            ActivityListResponse(
                status = true,
                message = "Success",
                detail = ActivityDetailData(
                    activityFor = listOf(
                        ActivityLeads(
                            activityLeadsId = "1",
                            activityLeadsName = "activity1",
                            isSelected = true
                        ),
                        ActivityLeads(
                            activityLeadsId = "2",
                            activityLeadsName = "activity2",
                            isSelected = true
                        ),
                        ActivityLeads(
                            activityLeadsId = "3",
                            activityLeadsName = "activity3",
                            isSelected = true
                        ),
                        ActivityLeads(
                            activityLeadsId = "4",
                            activityLeadsName = "activity3",
                            isSelected = true
                        ),
                    ),
                    activityType = listOf(
                       ActivityType(
                           activitytypeId = "1",
                            activitytypeName = "type1",
                           isSelected = true

                           ) ,
                        ActivityType(
                            activitytypeId = "2",
                            activitytypeName = "type2",
                            isSelected = true

                        ) ,
                        ActivityType(
                            activitytypeId = "3",
                            activitytypeName = "type3",
                            isSelected = true

                        ) ,
                    ),
                    notes = listOf(
                        NotesData(
                            notesId = "1",
                            notesName = "Not Interested",
                            bgColor = "#8576FD",
                            isSelected = false
                        ),
                        NotesData(
                            notesId = "2",
                            notesName = "Not Interested",
                            bgColor = "#03A0E2",
                            isSelected = false
                        ),
                        NotesData(
                            notesId = "3",
                            notesName = "Meeting arranged",
                            bgColor = "#03A0E2",
                            isSelected = false
                        ),
                        NotesData(
                            notesId = "4",
                            notesName = "Follow up",
                            bgColor = "#1AB1B0",
                            isSelected = false
                        ),
                    )
                ),

            )
        )
    }
}