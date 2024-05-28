package com.ndege.cheza.ndegecrunch.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ndege.cheza.ndegecrunch.domain.repositories.PreferenceDataStoreRepository
import com.ndege.cheza.ndegecrunch.domain.useCases.GetNdegeCrashDataUseCase
import com.ndege.cheza.ndegecrunch.domain.useCases.GetNdegeDetailsUseCase
import com.ndege.cheza.ndegecrunch.presentation.models.NdegeCrashViewModelState
import com.ndege.cheza.ndegecrunch.presentation.viewModels.events.PreferenceDataStoreEvents
import com.ndege.cheza.ndegecrunch.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.RoundingMode
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class GetNdegeCrashDataViewModel @Inject constructor(
    private val getNdegeCrashDataUseCase: GetNdegeCrashDataUseCase,
    private val getNdegeDetailsUseCase: GetNdegeDetailsUseCase,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NdegeCrashViewModelState())
    val state = _state.asStateFlow()

    init {
        getAccountBalance()
        getData()
        getDetails()
        startGame()
    }

    fun events(events: PreferenceDataStoreEvents) {
        when (events) {
            is PreferenceDataStoreEvents.SetAccountBalance -> {
            }

            is PreferenceDataStoreEvents.SetStakeAmount -> {
                _state.update {
                    it.copy(
                        stakeAmount = events.stakeAmount
                    )
                }
            }

            PreferenceDataStoreEvents.DecrementStakeAmount -> {
                decrementStakeAmount()
            }

            PreferenceDataStoreEvents.IncrementStakeAmount -> {
                incrementStakeAmount()
            }

            PreferenceDataStoreEvents.PlaceBet -> {
                placeBet()
            }
        }
    }

    private fun startGame() {
        viewModelScope.launch {
            if (state.value.totalTime > 0) {
                if (state.value.flyingTime > 0) {
                    delay(1.seconds)
                    _state.update {
                        it.copy(
                            totalTime = state.value.flyingTime + state.value.waitingTime
                        )
                    }
                }

                if (state.value.counter >= 1) {
                    delay(20)
                    _state.update {
                        it.copy(
                            counterDecimal = 1 + state.value.counterDecimal
                        )
                    }

                    val num = state.value.counterDecimal.toBigDecimal()
                        .divide(100.toBigDecimal(), 2, RoundingMode.HALF_UP)
                    _state.update {
                        it.copy(
                            totalAmountToWin = (state.value.counter + num.toDouble()) * state.value.stakeAmount,
                            crashPoint = state.value.counter + num.toDouble()
                        )
                    }

                    if (state.value.counterDecimal == 99) {
                        _state.update {
                            it.copy(
                                counter = state.value.counter + 1, counterDecimal = 0
                            )
                        }
                    }
                }

                if (state.value.flyingTime == 0) {
                    if (state.value.betIsRunning) {
                        _state.update {
                            it.copy(
                                betIsRunning = false
                            )
                        }
                        preferenceDataStoreRepository.saveAccountBalance(state.value.accountBalance - state.value.stakeAmount)
                    }
                    _state.update {
                        it.copy(
                            betIsRunning = false,
                            counter = 0,
                            crashPause = true,
                        )
                    }
                    delay(1.seconds)
                    _state.update {
                        it.copy(
                            waitingTime = state.value.waitingTime + 1,
                            totalTime = state.value.flyingTime + state.value.waitingTime
                        )
                    }
                    if (state.value.waitingTime < 6) {
                        state.value.crashPause = false
                    }
                }
            }

            if (state.value.waitingTime == 0) {
                if (state.value.betIsPlaced && state.value.accountBalance > state.value.stakeAmount) {
                    if (state.value.loggedIn) {
                        _state.update {
                            it.copy(
                                betIsRunning = true,
                                betIsPlaced = false,
                                flyingTime = (0..3).random()
                            )
                        }
                    }
                } else {
                    _state.update {
                        it.copy(
                            flyingTime = (2..80).random()
                        )
                    }
                }

                _state.update {
                    it.copy(
                        crashPoint = 0.00,
                        waitingTime = 7,
                        counter = 1,
                        counterDecimal = 0,
                        totalTime = state.value.waitingTime + state.value.flyingTime
                    )
                }
            }
        }
    }

    private fun placeBet() {
        viewModelScope.launch {
            if (!state.value.betIsRunning) {
                _state.update {
                    it.copy(
                        betIsPlaced = !state.value.betIsPlaced
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        betIsRunning = false, betWon = true
                    )
                }
                delay(3.seconds)
                val num = state.value.counterDecimal.toBigDecimal()
                    .divide(100.toBigDecimal(), 2, RoundingMode.HALF_UP)
                val totalAmountWon =
                    (state.value.counter + num.toDouble()) * state.value.stakeAmount
                preferenceDataStoreRepository.saveAccountBalance(state.value.accountBalance + totalAmountWon)
                _state.update {
                    it.copy(
                        betWon = false, amountWon = totalAmountWon
                    )
                }
            }
        }
    }

    private fun incrementStakeAmount() {
        viewModelScope.launch {
            if (!state.value.betIsRunning) {
                _state.update {
                    it.copy(
                        stakeAmount = state.value.stakeAmount + 10
                    )
                }
            }
        }
    }

    private fun decrementStakeAmount() {
        viewModelScope.launch {
            if (!state.value.betIsRunning) {
                if (state.value.stakeAmount > 10) {
                    _state.update {
                        it.copy(
                            stakeAmount = state.value.stakeAmount - 10
                        )
                    }
                }
            }
        }
    }

    private fun getAccountBalance() {
        viewModelScope.launch {
            val accountBalance: LiveData<Double> =
                preferenceDataStoreRepository.getAccountBalance().asLiveData()
            val userLoggedIn: LiveData<Boolean> =
                preferenceDataStoreRepository.getUserIsLoggedIn().asLiveData()
            accountBalance.value?.let { accountBalancePreference ->
                _state.update {
                    it.copy(
                        accountBalance = accountBalancePreference, loggedIn = userLoggedIn.value!!
                    )
                }
            }
        }
    }

    fun setShowLoginDialog(show: Boolean) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showLoginDialog = show
                )
            }
        }
    }

    fun setShowRegisterDialog(show: Boolean) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showRegisterDialog = show
                )
            }
        }
    }

    fun setShowValidatePaymentDialog(show: Boolean) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showValidatePaymentDialog = show
                )
            }
        }
    }

    fun enableDemoAccount(show: Boolean) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    enableDemoAccount = show
                )
            }
        }
    }

    private fun getDetails() {
        getNdegeDetailsUseCase().onEach { results ->
            when (results) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = results.errorMessage ?: "Unexpected error occurred"
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = results.isLoading
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            accountDetails = results.data
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getData() {
        getNdegeCrashDataUseCase().onEach { results ->
            when (results) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = results.errorMessage ?: "Unexpected error occurred"
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = results.isLoading
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            chatList = results.data?.chats!!, userList = results.data.users
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}