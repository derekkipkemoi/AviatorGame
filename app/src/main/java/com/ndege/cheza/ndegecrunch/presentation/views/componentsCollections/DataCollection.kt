package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections

import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel
import com.ndege.cheza.ndegecrunch.presentation.views.components.Anime
import com.ndege.cheza.ndegecrunch.presentation.views.components.CrashedCount
import com.ndege.cheza.ndegecrunch.presentation.views.components.PlayingCount
import com.ndege.cheza.ndegecrunch.presentation.views.components.SnackBar
import com.ndege.cheza.ndegecrunch.presentation.views.components.WaitingCount

@OptIn(UnstableApi::class)
@Composable
fun DataCollection(
    flyingDistance : Int,
    counter: Int,
    counterDecimal: Int,
    waitingTime: Int,
    totalAmountToWin: Double,
    crashPause: Boolean,
    crashPoint: Double,
    preferenceDataStoreViewModel: PreferenceDataStoreViewModel = hiltViewModel(),
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel()
) {
    val won by getNdegeCrashDataViewModel.state.collectAsState()
    val showAnimation by preferenceDataStoreViewModel.getShowAnimation().collectAsState(initial = false)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .border(
                border = BorderStroke(0.4.dp, color = Color.Gray), shape = RoundedCornerShape(3.dp)
            )
            .background(color = Color.Black, shape = RoundedCornerShape(3.dp)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
        ) {
            if (flyingDistance > 0) {
                if (showAnimation){
                    Anime()
                }
                if (won.betWon){
                    SnackBar("You cashed out", true)
                }
                PlayingCount(counter, counterDecimal)
            }
            if (crashPause) {
                CrashedCount(crashPoint)
            }
            if (waitingTime < 6) {
                WaitingCount(waitingTime)
            }
        }
    }
}