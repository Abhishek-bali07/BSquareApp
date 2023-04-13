package com.bsquare.core.entities


data class AppVersion(
    val versionCode: Int,
    val releaseDate: String,
    val versionMessage: String,
    val isSkipable: Boolean,
    val link: String
)

