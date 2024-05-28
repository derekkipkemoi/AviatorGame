package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PlayersStatsHeader(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "User",
            modifier = Modifier
                .weight(2.5F),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "Amount",
            modifier = Modifier
                .weight(2.5F),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "@",
            modifier = Modifier
                .weight(3F),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "Profit",
            modifier = Modifier
                .weight(3F),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
    }
}