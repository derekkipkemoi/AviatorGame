package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.cash.depositCash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CaptureAmount(
    selectedAmount: String,
    updateAmount: (String) -> Unit,
    showNextColumn: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.4.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp)
            )
            .background(color = Color.Black, shape = RoundedCornerShape(3.dp))
            .padding(10.dp)
    ) {
        Text(
            text = "Select or Enter amount",
            modifier = Modifier
                .background(
                    color = Color.White, shape = RoundedCornerShape(3.dp)
                )
                .padding(start = 10.dp, end = 10.dp),
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .height(1.dp)
                .background(color = Color.White)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "+100",
                modifier = Modifier
                    .background(
                        color = when (selectedAmount) {
                            "100" -> MaterialTheme.colorScheme.primary
                            else -> Color.White
                        },
                        shape = RoundedCornerShape(3.dp)
                    )
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
                    .clickable {
                        updateAmount("100")
                    },
                color = when (selectedAmount) {
                    "100" -> Color.White
                    else -> Color.Black
                },
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "+200",
                modifier = Modifier
                    .background(
                        color = when (selectedAmount) {
                            "200" -> MaterialTheme.colorScheme.primary
                            else -> Color.White
                        }, shape = RoundedCornerShape(3.dp)
                    )
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
                    .clickable {
                        updateAmount("200")
                    },
                color = when (selectedAmount) {
                    "200" -> Color.White
                    else -> Color.Black
                },
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "+500",
                modifier = Modifier
                    .background(
                        color = when (selectedAmount) {
                            "500" -> Color.Black
                            else -> Color.White
                        }, shape = RoundedCornerShape(3.dp)
                    )
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
                    .clickable {
                        updateAmount("500")
                    },
                color = when (selectedAmount) {
                    "500" -> Color.White
                    else -> Color.Black
                },
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "+1000",
                modifier = Modifier
                    .background(
                        color = when (selectedAmount) {
                            "1000" -> Color.Black
                            else -> Color.White
                        }, shape = RoundedCornerShape(3.dp)
                    )
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
                    .clickable {
                        updateAmount("1000")
                    },
                color = when (selectedAmount) {
                    "1000" -> Color.White
                    else -> Color.Black
                },
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Row(
            modifier = Modifier.padding(start = 15.dp)
        ) {
            OutlinedTextField(value = selectedAmount, onValueChange = {
                updateAmount(it)
            }, colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.White,
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = Color.White,
                focusedTextColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedTextColor = Color.White
            ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = TextStyle.Default.copy(fontSize = 16.sp), label = {
                    Text(
                        text = "Enter amount", style = MaterialTheme.typography.labelSmall
                    )
                })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = {
                       showNextColumn()
                },
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    containerColor = when(selectedAmount.length > 1){
                        true -> MaterialTheme.colorScheme.tertiary
                        else -> Color.Black
                    },
                ),
                enabled = selectedAmount.length > 1,
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.tertiary)
            ) {
                Text(text = "Confirm Amount")
            }
        }


    }
}