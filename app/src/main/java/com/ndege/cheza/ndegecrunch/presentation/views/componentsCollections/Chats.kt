package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.ndege.cheza.ndegecrunch.presentation.components.UserMessage
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel

@Composable
fun Chats(
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel()
){
    val data by getNdegeCrashDataViewModel.state.collectAsState()
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .border(width = 1.dp, color = Color.Black)
        .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "User",
                modifier = Modifier.weight(3F)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.labelSmall)
            Text(text = "Message",
                modifier = Modifier.weight(7F)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.labelSmall)
        }
        LazyColumn {
            data.chatList.let { chatList ->
                items(count = chatList.size){ messageItem ->
                    UserMessage(chatList[messageItem])
                }
            }
        }
        if (data.isLoading){
            CircularProgressIndicator(
                color = Color.White
            )
        }
        if (data.errorMessage.isNotEmpty()){
            Text(text = data.errorMessage,
                color = Color.White,
                style = MaterialTheme.typography.labelSmall)
        }
    }
}