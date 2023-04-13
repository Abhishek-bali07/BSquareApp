package com.bsquare.core.utils.helper

import android.content.ContentResolver

interface Info {
    fun getCurrentVersion(): Int
    //fun getDeviceId(resolver: ContentResolver): String
}