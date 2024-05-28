package com.ndege.cheza.ndegecrunch.presentation.models

import com.ndege.cheza.ndegecrunch.data.remote.dto.Chat
import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeDetails
import com.ndege.cheza.ndegecrunch.data.remote.dto.User

data class NdegeCrashViewModelState(
    val errorMessage: String = "",
    val chatList: List<Chat> = arrayListOf(),
    val userList: List<User> = arrayListOf(),
    val betWon: Boolean = false,
    val amountWon: Double = 0.00,
    val accountBalance: Double = 0.00,
    val loggedIn : Boolean = false,
    val showAccountDialog: Boolean = false,
    val showLoginDialog: Boolean = false,
    val showRegisterDialog: Boolean = false,
    val showValidatePaymentDialog: Boolean = false,
    val enableDemoAccount: Boolean = true,
    val accountDetails: NdegeDetails? = null,
    val isLoading: Boolean = false,

    val counter: Int = 1,
    var flyingTime: Int = (2..20).random(),
    val counterDecimal: Int = 0,
    var stakeAmount: Int = 10,
    var totalAmountToWin: Double = stakeAmount.toDouble(),
    var crashPause: Boolean = false,
    var crashPoint: Double = 0.0,
    var waitingTime: Int = 7,
    var betIsPlaced: Boolean = false,
    var betIsRunning: Boolean = false,
    var totalTime: Int = flyingTime + waitingTime,
)
