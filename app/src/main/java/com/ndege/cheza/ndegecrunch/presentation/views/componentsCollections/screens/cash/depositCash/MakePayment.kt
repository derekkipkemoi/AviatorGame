package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.cash.depositCash

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ndege.cheza.ndegecrunch.R

@Composable
fun MakePayment(
    tillNumber: Int,
    selectedAmount: String
){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.4.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(3.dp)
            )
            .background(color = Color.Black, shape = RoundedCornerShape(3.dp))
            .padding(10.dp)
    ) {
        Text(
            text = "Make Payment",
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "1. ", color = Color.Gray,style = MaterialTheme.typography.labelMedium)
                Text(text = "Go to M-PESA", style = MaterialTheme.typography.labelMedium)
            }
            Image(
                painter = painterResource(id = R.drawable.mpesa), contentDescription = "Mpesa",
                modifier = Modifier.height(45.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .height(0.5.dp)
                .background(color = Color.White)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "2. ",color = Color.Gray, style = MaterialTheme.typography.labelMedium)
                Text(text = "Select",style = MaterialTheme.typography.labelMedium)
            }
            Text(text = "Lipa na M-Pesa",
                color = Color.Gray,
                style = MaterialTheme.typography.labelMedium)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .height(0.5.dp)
                .background(color = Color.White)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "3. ", color = Color.Gray,style = MaterialTheme.typography.labelMedium)
                Text(text = "Select",style = MaterialTheme.typography.labelMedium)
            }
            Text(text = "Buy Goods",
                color = Color.Gray,
                style = MaterialTheme.typography.labelMedium,
                )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .height(0.5.dp)
                .background(color = Color.White)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "4. ", color = Color.Gray,style = MaterialTheme.typography.labelMedium)
                Text(text = "Enter till no",style = MaterialTheme.typography.labelMedium)
            }
            Row(
                modifier = Modifier.clickable {
                    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData: ClipData = ClipData.newPlainText("text", tillNumber.toString())
                    clipboardManager.setPrimaryClip(clipData)
                    Toast.makeText(context, "Till $tillNumber Copied", Toast.LENGTH_LONG).show()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = tillNumber.toString(),   color = Color.Gray, style = MaterialTheme.typography.labelMedium)
                Text(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .padding(end = 10.dp, start = 10.dp),
                    text = "COPY",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .height(0.5.dp)
                .background(color = Color.White)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "5. ",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray)
                Text(text = "Enter Amount",style = MaterialTheme.typography.labelMedium)
            }
            Text(text = "Ksh $selectedAmount",
                color = Color.Gray,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}