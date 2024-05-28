package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.events.PreferenceDataStoreEvents
import kotlinx.coroutines.delay
import java.math.RoundingMode
import kotlin.time.Duration.Companion.seconds

@Composable
fun ComponentsCollections(
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel()

) {
    val state by getNdegeCrashDataViewModel.state.collectAsState()
    val events = getNdegeCrashDataViewModel::events

    fun placeBet() {
        events(PreferenceDataStoreEvents.PlaceBet)
    }

    fun incrementStakeAmount() {
        events(PreferenceDataStoreEvents.IncrementStakeAmount)
    }

    fun decrementStakeAmount() {
        events(PreferenceDataStoreEvents.DecrementStakeAmount)
    }

    fun setStakeAmount(stakeAmountNew: Int) {
        events(PreferenceDataStoreEvents.SetStakeAmount(stakeAmountNew))
    }

    Column(
        modifier = Modifier.padding(top = 2.5.dp, start = 5.dp, end = 5.dp)
    ) {
        DataCollection(
            state.flyingTime,
            state.counter,
            state.counterDecimal,
            state.waitingTime,
            state.totalAmountToWin,
            state.crashPause,
            state.crashPoint
        )
        PlaceBet(placeBet = { placeBet() },
            state.betIsPlaced,
            state.betIsRunning,
            state.stakeAmount,
            state.totalAmountToWin,
            incrementStakeAmount = { incrementStakeAmount() },
            decrementStakeAmount = { decrementStakeAmount() },
            setStakeAmount = {
                setStakeAmount(it)
            })
        Stats(state.flyingTime, state.waitingTime, state.counter, state.counterDecimal)
    }
}