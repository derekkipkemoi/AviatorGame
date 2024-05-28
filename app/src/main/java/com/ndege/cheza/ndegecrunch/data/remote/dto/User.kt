package com.ndege.cheza.ndegecrunch.data.remote.dto


import com.squareup.moshi.Json

data class User(
    @Json(name = "amount")
    val amount: String,
    @Json(name = "phoneNumber")
    val phoneNumber: String,
    @Json(name = "userName")
    val userName: String,
    @Json(name = "userWon")
    var userWon : Boolean = false
)