package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import java.text.DecimalFormat

@Composable
fun SnackBar(
    message: String,
    won: Boolean,
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel()
) {
    val state by getNdegeCrashDataViewModel.state.collectAsState()
    val df = DecimalFormat("0.00")
    Snackbar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp)
            .border(
                width = 0.4.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(3.dp)),
        shape = RoundedCornerShape(3.dp),
        containerColor = when (won) {
            true -> MaterialTheme.colorScheme.tertiary
            else -> Color.Red
        },
        contentColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = df.format(state.amountWon) + " KES",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

    }
}