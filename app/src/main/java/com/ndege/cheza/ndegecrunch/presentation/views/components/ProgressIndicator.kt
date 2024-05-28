package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(){
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(150.dp)
            .background(color = Color.White, shape = RoundedCornerShape(3.dp))
            .clip(shape = RoundedCornerShape(3.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Please wait...", color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall
        )
        CircularProgressIndicator()
    }
}