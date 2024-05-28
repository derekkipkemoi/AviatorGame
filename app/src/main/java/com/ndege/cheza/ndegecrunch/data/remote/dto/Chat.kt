package com.ndege.cheza.ndegecrunch.data.remote.dto


import com.squareup.moshi.Json

data class Chat(
    @Json(name = "msg")
    val msg: String,
    @Json(name = "user")
    val user: String
)