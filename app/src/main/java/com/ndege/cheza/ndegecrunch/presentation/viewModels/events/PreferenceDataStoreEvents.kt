package com.ndege.cheza.ndegecrunch.presentation.viewModels.events

sealed interface PreferenceDataStoreEvents {
    data class SetAccountBalance(val accountBalance: Double) : PreferenceDataStoreEvents
    data class SetStakeAmount(val stakeAmount: Int) : PreferenceDataStoreEvents
    data object IncrementStakeAmount : PreferenceDataStoreEvents
    data object DecrementStakeAmount : PreferenceDataStoreEvents
    data object PlaceBet : PreferenceDataStoreEvents
}