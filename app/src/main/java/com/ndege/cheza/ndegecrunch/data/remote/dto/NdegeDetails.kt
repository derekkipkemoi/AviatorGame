package com.ndege.cheza.ndegecrunch.data.remote.dto


import com.squareup.moshi.Json

data class NdegeDetails(
    @Json(name = "showAviator")
    val showAviator: Boolean,
    @Json(name = "tillName")
    val tillName: String,
    @Json(name = "tillNumber")
    val tillNumber: Int
)