package com.ndege.cheza.ndegecrunch.presentation.views.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ndege.cheza.ndegecrunch.R
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel
import java.text.DecimalFormat

@SuppressLint("ResourceAsColor")
@Composable
fun PlaceBetButton(
    placeBet: () -> Unit,
    stakeAmount: Int,
    totalAmountToWin: Double,
    betIsPlaced: Boolean,
    betIsRunning: Boolean,
    preferenceDataStoreViewModel : PreferenceDataStoreViewModel = hiltViewModel()
) {
    val df = DecimalFormat("0.00")
    var buttonText by remember {
        mutableStateOf("PLACE BET")
    }
    val accountBalance by preferenceDataStoreViewModel.getAccountBalance().collectAsState(0.00)
    val userLoggedIn by preferenceDataStoreViewModel.getUserLoggedIn().collectAsState(false)

    var buttonColor by remember {
        mutableIntStateOf(R.color.green)
    }

    if (betIsRunning) {
         buttonText = "CASH OUT"
         buttonColor = R.color.yellow
    }else{
        if (!betIsPlaced){
            buttonText = "PLACE BET"
            buttonColor = R.color.green
        }else{
            buttonText = "CANCEL BET"
            buttonColor = R.color.red
        }
     }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp, color = Color.Black, shape = RoundedCornerShape(3.dp)
            )
            .background(
                color = colorResource(id = buttonColor), shape = RoundedCornerShape(3.dp)
            )
            .padding(top = 15.dp, bottom = 15.dp)
            .clickable {
                if (userLoggedIn){
                    if (accountBalance >= stakeAmount && stakeAmount >= 10) {
                        placeBet()
                    } else {
                        Toast
                            .makeText(context, "Low Account Balance, Top Up", Toast.LENGTH_LONG)
                            .show()
                    }
                }else{
                    Toast
                        .makeText(context, "Please Login or Register Account", Toast.LENGTH_LONG)
                        .show()
                }


            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buttonText, color = Color.White, style = MaterialTheme.typography.bodyMedium
        )

        if (betIsRunning){
            Text(
                text = df.format(totalAmountToWin) + " KES", color = Color.White, style = MaterialTheme.typography.bodyMedium
            )
        }else{
            Text(
                text = df.format(stakeAmount) + " KES", color = Color.White, style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}