package com.bsquare.core.entities

data class TaskDetailData(
    val taskFor: List<Task>,
    val taskType: List<Type>,
    val taskAssign: List<Assign>
)
