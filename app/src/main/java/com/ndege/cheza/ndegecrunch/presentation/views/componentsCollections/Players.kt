package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ndege.cheza.ndegecrunch.data.remote.dto.User
import com.ndege.cheza.ndegecrunch.presentation.components.User
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@SuppressLint("UnrememberedMutableState")
@Composable
fun Players(
    counter: Int,
    counterDecimal: Int,
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel()
) {
    val data by getNdegeCrashDataViewModel.state.collectAsState()

    var randomUserIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val usersToPresent = remember {
        mutableStateListOf<User>()
    }

    val usersToPresentWinners = remember {
        mutableStateListOf<User>()
    }


    var dataInitialized by rememberSaveable {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    data.userList.let {
        coroutineScope.launch {
            when (data.waitingTime) {
                6 -> {
                    dataInitialized = true
                    usersToPresent.clear()
                    usersToPresentWinners.clear()
                }

                in 3..5 -> {
                    usersToPresent.shuffle()
                    it.shuffled()
                    for (user in it) {
                        delay(10)
                        user.userWon = false
                        usersToPresent.add(user)
                    }
                }


                7 -> {
                    if (dataInitialized && counter >= 2) {
                        while (usersToPresentWinners.size < usersToPresent.size) {
                            randomUserIndex = (0..<usersToPresent.size).random()
                            delay((1..10).random().seconds)
                            if (randomUserIndex < usersToPresent.size && usersToPresent[randomUserIndex] !in usersToPresentWinners) {
                                val userToAdd = usersToPresent[randomUserIndex]
                                usersToPresent.removeAt(randomUserIndex)
                                userToAdd.userWon = true
                                usersToPresent.add(randomUserIndex, userToAdd)
                                usersToPresentWinners.add(userToAdd)
                            }
                        }
                    }
                    if (!dataInitialized && counter >= 2) {
                        if (usersToPresent.isEmpty()) {
                            usersToPresent.addAll(it)
                        }
                        while (usersToPresentWinners.size < usersToPresent.size) {
                            randomUserIndex = (0..<usersToPresent.size).random()
                            delay((1..10).random().seconds)
                            if (randomUserIndex < usersToPresent.size && usersToPresent[randomUserIndex] !in usersToPresentWinners) {
                                val userToAdd = usersToPresent[randomUserIndex]
                                usersToPresent.removeAt(randomUserIndex)
                                userToAdd.userWon = true
                                usersToPresent.add(randomUserIndex, userToAdd)
                                usersToPresentWinners.add(userToAdd)
                            }
                        }
                    }
                }
            }
        }
    }





    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(width = 1.dp, color = Color.Black)
            .background(color = Color.Black), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(count = usersToPresent.size) { index ->
                User(usersToPresent[index], counter, counterDecimal)
            }
        }
        if (data.isLoading) {
            CircularProgressIndicator(
                color = Color.White
            )
        }
        if (data.errorMessage.isNotEmpty()) {
            Text(
                text = data.errorMessage,
                color = Color.White,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

