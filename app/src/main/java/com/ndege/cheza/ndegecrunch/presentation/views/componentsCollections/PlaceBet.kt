package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ndege.cheza.ndegecrunch.presentation.views.components.EditBetAmount
import com.ndege.cheza.ndegecrunch.presentation.views.components.PlaceBetButton

@Composable
fun PlaceBet(
    placeBet: () -> Unit,
    betIsPlaced: Boolean,
    betIsRunning: Boolean,
    stakeAmount: Int,
    totalAmountToWin: Double,
    incrementStakeAmount: () -> Unit,
    decrementStakeAmount: () -> Unit,
    setStakeAmount: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.5.dp)
            .border(width = 0.4.dp, color = Color.Gray, shape = RoundedCornerShape(3.dp))
            .background(color = Color.Black, shape = RoundedCornerShape(3.dp)),
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(end = 3.dp)
            ) {
                EditBetAmount(betIsRunning,stakeAmount, incrementStakeAmount, decrementStakeAmount, setStakeAmount, )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(start = 3.dp)
            ) {
                PlaceBetButton(placeBet,stakeAmount,totalAmountToWin, betIsPlaced, betIsRunning)
            }
        }
    }

}