package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Stats(
    flyingTime: Int,
    waitingTime: Int,
    counter: Int,
    counterDecimal: Int
) {
    var selectedTab by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.5.dp)
            .border(
                width = 0.4.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp)
            )
            .background(
                color = Color.Black, shape = RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp)
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(3.5.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(3.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(2.5.dp)
                    .background(
                        color = when (selectedTab) {
                            true -> Color.Black
                            false -> MaterialTheme.colorScheme.primary
                        }, shape = RoundedCornerShape(3.dp)
                    )
                    .clickable { selectedTab = true }
            ) {
                Text(
                    text = "Users",
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Column(
                modifier = Modifier
                    .padding(2.5.dp)
                    .background(
                        color = when (selectedTab) {
                            true -> MaterialTheme.colorScheme.primary
                            false -> Color.Black
                        }, shape = RoundedCornerShape(3.dp)
                    )
                    .clickable { selectedTab = false }
            ) {
                Text(
                    text = "Chats",
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                    style = MaterialTheme.typography.labelMedium
                )
            }

        }
        Spacer(modifier = Modifier.fillMaxWidth()
            .height(1.dp)
            .background(color = MaterialTheme.colorScheme.primary))
        if (selectedTab) {
            Players(counter, counterDecimal)
        } else {
            Chats()
        }

    }

}