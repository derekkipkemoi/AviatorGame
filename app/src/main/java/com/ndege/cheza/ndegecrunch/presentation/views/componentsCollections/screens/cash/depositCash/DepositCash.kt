package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.cash.depositCash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ndege.cheza.ndegecrunch.presentation.components.ScreensHeader
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel

@Composable
fun DepositCash(
    navController: NavController,
    getNdegeCrashDataViewModel : GetNdegeCrashDataViewModel = hiltViewModel(),
    preferenceDataStoreViewModel: PreferenceDataStoreViewModel = hiltViewModel()
) {
    val state by getNdegeCrashDataViewModel.state.collectAsState()

    var playSound by remember {
        mutableStateOf(false)
    }

    var selectedAmount by remember {
        mutableStateOf("100")
    }

    var confirmed by remember {
        mutableStateOf(false)
    }

    fun showNextColumn() {
        confirmed = true
    }

    fun updateAmount(amountValue: String) {
        selectedAmount = amountValue
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize()
        ) {
            ScreensHeader(title = "Deposit", navController)
        }

        Column(
            modifier = Modifier
                .weight(8F)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            CaptureAmount(selectedAmount = selectedAmount, updateAmount = {
                updateAmount(it)
            }, showNextColumn = {
                showNextColumn()
            })
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
            if (confirmed){
                MakePayment(state.accountDetails!!.tillNumber, selectedAmount)
            }
        }
       if (confirmed){
           Row(
               modifier = Modifier
                   .weight(1F)
                   .fillMaxWidth()
                   .background(color = Color.White)
           ) {
               ValidatePaymentsDialog(tillName = state.accountDetails!!.tillName, showNextColumn = confirmed, popBackStack = {
                  navController.popBackStack()
               })
           }
       }
    }

}

