package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.cash.withdrawCash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ndege.cheza.ndegecrunch.R
import com.ndege.cheza.ndegecrunch.presentation.components.ScreensHeader
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel

@Composable
fun WithdrawCash(
    navController: NavController,
    preferenceDataStoreViewModel: PreferenceDataStoreViewModel = hiltViewModel()
) {
    val accountBalance by  preferenceDataStoreViewModel.getAccountBalance().collectAsState(0.00)
    var amountToWithDraw by remember {
        mutableDoubleStateOf(0.00)
    }

    var error by remember {
        mutableStateOf(false)
    }

    fun startWithDraw(){
        error = if (accountBalance > amountToWithDraw){
            false
        }else{
            true
        }
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
            ScreensHeader(title = "Withdraw", navController)
        }

        Column(
            modifier = Modifier
                .weight(9F)
                .padding(20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    width = 0.4.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(3.dp))
                .background(color = Color.Black, shape = RoundedCornerShape(3.dp))
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.mpesa),
                contentDescription = "Mpesa Icon",
                modifier = Modifier.height(100.dp)
            )

            OutlinedTextField(
                value = amountToWithDraw.toString(),
                onValueChange = {
                                amountToWithDraw = it.toDouble()
                },
                colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.White,
                        focusedContainerColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = Color.White,
                        focusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                label = {
                    Text(
                        text = "Enter amount", style = MaterialTheme.typography.labelSmall
                    )
                },
                placeholder = {
                              Text(text = "100")
                },
                leadingIcon = {
                    Image(painter = painterResource(id = R.drawable.money_24), contentDescription = "Cash")
                }
            )
            if (error){
                Text(text = "Account balance low", style = MaterialTheme.typography.labelSmall, color = Color.Red)
            }


            Button(
                onClick = {
                          startWithDraw()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary, contentColor = Color.White
                )
            ) {
                Text(text = "WITHDRAW", style = MaterialTheme.typography.labelMedium)
            }
        }

    }
}