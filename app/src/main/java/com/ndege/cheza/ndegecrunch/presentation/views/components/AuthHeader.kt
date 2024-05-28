package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ndege.cheza.ndegecrunch.R

@Composable
fun AuthHeader(
    authTitle: String,
    showAlertDialog: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = authTitle,
            style = MaterialTheme.typography.bodyMedium
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20.dp)
                )
        )
        {
            Image(
                painter = painterResource(id = R.drawable.close),
                contentDescription = "Close Icon",
                modifier = Modifier
                    .padding(10.dp)
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        showAlertDialog()
                    }
            )
        }
    }
}