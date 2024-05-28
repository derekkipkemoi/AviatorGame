package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ndege.cheza.ndegecrunch.data.remote.dto.User
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun User(
    user: User, counter: Int, counterDecimal: Int
) {
    val df = DecimalFormat("0.00")
    var totalAmountToWin by rememberSaveable {
        mutableDoubleStateOf(0.00)
    }
    var runningCashPoint by rememberSaveable {
        mutableDoubleStateOf(0.00)
    }
    var wonAmount = 0.00
    var cashPoint = 0.00
    val num = counterDecimal.toBigDecimal().divide(100.toBigDecimal(), 2, RoundingMode.HALF_UP)
    if (user.userWon) {
        wonAmount = totalAmountToWin
        cashPoint = runningCashPoint
    } else {
        totalAmountToWin = (counter + num.toDouble()) * user.amount.toDouble()
        runningCashPoint = counter + num.toDouble()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = when (user.userWon) {
                        true -> MaterialTheme.colorScheme.tertiary
                        false -> MaterialTheme.colorScheme.primary
                    }, shape = RoundedCornerShape(3.dp)
                )
                .padding(1.5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user.phoneNumber,
                modifier = Modifier.weight(2.5F),
                color =  MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = user.amount,
                modifier = Modifier.weight(2.5F),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
            if (user.userWon) {
                Text(
                    text = df.format(cashPoint)+" x",
                    modifier = Modifier
                        .weight(2.5F)
                        .padding(2.dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(10.dp)),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
            } else {
                Text(
                    text = df.format(runningCashPoint)+" x",
                    modifier = Modifier.weight(2.5F),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            if (user.userWon) {
                Text(
                    text = df.format(wonAmount),
                    modifier = Modifier.weight(3F),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
            } else {
                Text(
                    text = df.format(totalAmountToWin),
                    modifier = Modifier.weight(3F),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
            }


        }
    }

}