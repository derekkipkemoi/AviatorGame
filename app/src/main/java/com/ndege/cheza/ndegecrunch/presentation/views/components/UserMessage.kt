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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ndege.cheza.ndegecrunch.data.remote.dto.Chat

@Composable
fun UserMessage(
    chat: Chat
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(3.dp)
                )
                .padding(1.5.dp), horizontalArrangement = Arrangement.Start
        ) {
            Text(text = chat.user+" :",
                modifier = Modifier.weight(3F).padding(start = 10.dp),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelSmall)
            Text(text = chat.msg,
                modifier = Modifier.weight(7F),
                style = MaterialTheme.typography.labelSmall)

        }
    }
}