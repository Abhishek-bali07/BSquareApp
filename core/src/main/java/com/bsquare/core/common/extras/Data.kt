package com.bsquare.core.common.extras

import com.bsquare.core.common.enums.EmitType


data class Data(
    val type: EmitType,
    val value: Any?
)
