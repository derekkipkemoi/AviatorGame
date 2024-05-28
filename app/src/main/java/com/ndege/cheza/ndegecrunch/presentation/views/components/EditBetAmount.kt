package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ndege.cheza.ndegecrunch.R
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel

@Composable
fun EditBetAmount(
    betIsRunning: Boolean,
    stakeAmount: Int,
    incrementStakeAmount: ()-> Unit,
    decrementStakeAmount: ()-> Unit,
    setStakeAmount: (Int)-> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(3.dp)
            )
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp, color = Color.Black, shape = RoundedCornerShape(3.dp)
                )
                .background(
                    color = Color.Black, shape = RoundedCornerShape(3.dp)
                )
                .padding(top = 5.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(id = R.drawable.minus),
                contentDescription = "Add Icon",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .width(20.dp)
                    .clickable {
                        if (!betIsRunning){
                            if (stakeAmount > 10) {
                                decrementStakeAmount()
                            }
                        }
                    })
            Row {
                Text(
                    text = "$stakeAmount.00",
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Image(painter = painterResource(id = R.drawable.add),
                contentDescription = "Minus Icon",
                modifier = Modifier
                    .padding(end = 5.dp)
                    .width(20.dp)
                    .clickable {
                        if (!betIsRunning){
                            incrementStakeAmount()
                        }
                    })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .border(
//                    width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)
//                )
                .padding(top = 5.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier
                .background(
                    color = Color.Black, shape = RoundedCornerShape(3.dp)
                )
                .padding(end = 7.dp, start = 7.dp)
                .clickable {
                    if (!betIsRunning){
                        setStakeAmount(100)
                    }
                }) {
                Text(
                    text = "100", color = Color.White, style = MaterialTheme.typography.labelMedium
                )
            }
            Row(modifier = Modifier
                .background(
                    color = Color.Black, shape = RoundedCornerShape(3.dp)
                )
                .padding(end = 7.dp, start = 7.dp)
                .clickable {
                    if (!betIsRunning){
                        setStakeAmount(500)
                    }
                }) {
                Text(
                    text = "500", color = Color.White, style = MaterialTheme.typography.labelMedium
                )
            }
            Row(modifier = Modifier
                .background(
                    color = Color.Black, shape = RoundedCornerShape(3.dp)
                )
                .padding(end = 7.dp, start = 7.dp)
                .clickable {
                    if (!betIsRunning){
                        setStakeAmount(1000)
                    }
                }) {
                Text(
                    text = "1000", color = Color.White, style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }

}