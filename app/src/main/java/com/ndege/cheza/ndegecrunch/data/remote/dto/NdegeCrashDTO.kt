package com.ndege.cheza.ndegecrunch.data.remote.dto


import com.squareup.moshi.Json

data class NdegeCrashDTO(
    @Json(name = "chats")
    val chats: List<Chat>,
    @Json(name = "users")
    val users: List<User>
)