package com.ndege.cheza.ndegecrunch.presentation.views.components

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ndege.cheza.ndegecrunch.R
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun LogOutCard(
    showProgressIndicator: () -> Unit,
) {
    var showCard by remember {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {
                showCard = true
            },
            shape = RoundedCornerShape(3.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = Color.White
            )
        ) {
            Image(painter = painterResource(id = R.drawable.logout_24), contentDescription = "",
                modifier = Modifier.width(28.dp).height(26.dp).clickable {
                    showCard = !showCard
                })
        }
    }


            if (showCard){
            AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ },
                modifier = Modifier
                    .border(
                        width = 0.4.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(3.dp)
                    ),
                shape = RoundedCornerShape(3.dp),
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AuthHeader("Account", showAlertDialog = {
                            showCard = false
                        })
                    }
                }, text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                showCard = false
                                showProgressIndicator()
                            },
                            shape = RoundedCornerShape(3.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = Color.White
                            ), modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "CONFIRM LOGOUT")
                        }
                    }
                }

            ) }

}