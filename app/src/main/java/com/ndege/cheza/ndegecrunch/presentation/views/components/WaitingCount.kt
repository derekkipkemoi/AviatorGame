package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WaitingCount(timer: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "NEXT ROUND IN", color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
        Surface(
            color = Color.Transparent, // This avoids the color to overlap with the background
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0x20FFFFFF), // Starting color of the gradient
                            Color(0x20FFFFFF) // Ending color of the gradient
                        )
                    ),
                    shape = RoundedCornerShape(50.dp) // Fancy rounded corners
                )
                .padding(start = 20.dp, end = 20.dp) // Add padding after background for proper gradient inclusion
        ){
            Text(
                text = "$timer", color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}