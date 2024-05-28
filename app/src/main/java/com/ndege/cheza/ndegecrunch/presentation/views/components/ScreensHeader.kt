package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreensHeader(
    title: String,
    navController: NavController
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .height(40.dp)
                .width(40.dp)
                .background(color = Color.Black, shape = RoundedCornerShape(3.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Filled.ArrowBack,
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "Back Arrow")
        }
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}