package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun Timer() {
    var decimalValue by remember { mutableIntStateOf(0) }
    var counterValue by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = decimalValue) {
        if (decimalValue >= 0) {
            delay(20)
            decimalValue += 1
        }
        if (decimalValue >= 99){
            counterValue += 1
            decimalValue = 0
        }
    }

    Text(text = "$counterValue . $decimalValue", color = Color.White)
}