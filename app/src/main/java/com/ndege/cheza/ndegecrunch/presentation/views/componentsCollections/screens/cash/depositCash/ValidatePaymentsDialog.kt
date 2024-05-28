package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.cash.depositCash

import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ndege.cheza.ndegecrunch.R
import com.ndege.cheza.ndegecrunch.presentation.components.ProgressIndicator
import com.ndege.cheza.ndegecrunch.presentation.components.ProgressIndicatorCard
import com.ndege.cheza.ndegecrunch.presentation.views.navigation.NavigationRoutes
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun ValidatePaymentsDialog(
    tillName: String,
    showNextColumn: Boolean,
    popBackStack: ()-> Unit,
    preferenceDataStoreViewModel: PreferenceDataStoreViewModel = hiltViewModel(),
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val accountBalance by preferenceDataStoreViewModel.getAccountBalance().collectAsState(0.00)
    val mpesaCodeViewModelState by preferenceDataStoreViewModel.getMpesaCode()
        .collectAsState(initial = "DSGHTRD453")
    val state by getNdegeCrashDataViewModel.state.collectAsState()
    val mpesaMessage = remember { mutableStateOf("") }
    val mpesaMessageError = remember { mutableStateOf(false) }
    val errorMessage = remember {
        mutableStateOf("")
    }
    val code = remember {
        mutableStateOf("")
    }
    var amountToDisplay by remember {
        mutableStateOf("")
    }

    var showMessageDetails by remember {
        mutableStateOf(false)
    }

    var amountToSave by remember {
        mutableIntStateOf(0)
    }

    var textFieldReadOnly by remember {
        mutableStateOf(false)
    }

    val startToSave = remember {
        mutableStateOf(false)
    }

    if (startToSave.value) {
        LaunchedEffect(Unit) {
            delay(3.seconds)
            preferenceDataStoreViewModel.saveMpesaCode(code.value)
            getNdegeCrashDataViewModel.setShowValidatePaymentDialog(false)
            preferenceDataStoreViewModel.setAccountBalance(amountToSave + accountBalance)
            startToSave.value = false
            Toast.makeText(context, "Deposit confirmed successfully", Toast.LENGTH_LONG).show()
            popBackStack()
        }
    }

    if (mpesaMessage.value.length > 30) {
        val mString = mpesaMessage.value.split(" ").toTypedArray()
        code.value = mString[0]
        amountToDisplay = mString[2]
        val amountInStringWithDouble = mString[2].substringAfterLast("Ksh")
        amountToSave = amountInStringWithDouble.replace(",", "").toDouble().toInt()
        showMessageDetails = true
        textFieldReadOnly = true
    }


    OutlinedButton(
        onClick = {
            getNdegeCrashDataViewModel.setShowValidatePaymentDialog(true)
        },
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White,
            containerColor = when (showNextColumn) {
                true -> MaterialTheme.colorScheme.tertiary
                else -> Color.Black
            },
        ),
        enabled = showNextColumn,
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.tertiary)
    ) {
        Text(text = "Validate Payments", style = MaterialTheme.typography.labelMedium)
    }

    fun getMpesaMessageFromClipBoard() {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboardManager.primaryClip!!.getItemAt(0) != null) {
            val item = clipboardManager.primaryClip!!.getItemAt(0)
            mpesaMessage.value = item.text.toString()
        } else {
            Toast.makeText(context, "No message copied to clipboard", Toast.LENGTH_LONG).show()
        }
    }

    fun saveDetails() {
        if (code.value == mpesaCodeViewModelState) {
            mpesaMessageError.value = true
            errorMessage.value = "Provide valid M-PESA message"
            return
        } else {
            mpesaMessageError.value = false
            errorMessage.value = ""
        }

        if (!mpesaMessage.value.contains(tillName)) {
            mpesaMessageError.value = true
            errorMessage.value = "Provide valid M-PESA message"
            return
        } else {
            mpesaMessageError.value = false
            errorMessage.value = ""
        }


//        if (amountToSave.toString() != amountToDisplay) {
//            mpesaMessageError.value = true
//            errorMessage.value = "Provide valid M-PESA message"
//            return
//        } else {
//            mpesaMessageError.value = false
//            errorMessage.value = ""
//        }

        if (code.value.length < 10) {
            mpesaMessageError.value = true
            errorMessage.value = "Provide valid M-PESA message"
            return
        } else {
            mpesaMessageError.value = false
            errorMessage.value = ""
        }

        startToSave.value = true
    }

    if (state.showValidatePaymentDialog) {
        AlertDialog(
            modifier = Modifier
                .border(
                    width = 0.4.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(3.dp)
                ),
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(3.dp),
            onDismissRequest = {},
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(4F)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Validate Payments",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                            .weight(1F)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(35.dp)
                                .clip(shape = CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = {
                                    getNdegeCrashDataViewModel.setShowValidatePaymentDialog(false)
                                }, modifier = Modifier
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Icon",
                                    tint = Color.White
                                )
                            }
                        }

                    }
                }
            },
            text = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(
                            text = "Copy the entire confirmation message you received from M-PESA and paste in the text field below then click verify button",
                            style = MaterialTheme.typography.labelSmall,
                        )
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                            getMpesaMessageFromClipBoard()
                        },
                            shape = RoundedCornerShape(3.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.tertiary
                            )
                            ) {
                            Text(
                                text = "CLICK TO PASTE",
                                style = MaterialTheme.typography.labelMedium,
                            )
                        }

                        if (showMessageDetails) {
                            Row {
                                Text(
                                    text = "MESSAGE CODE",
                                    modifier = Modifier.padding(5.dp),
                                    style = MaterialTheme.typography.labelSmall
                                )
                                Row(
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(10.dp))
                                        .background(color = Color.Black)
                                        .padding(5.dp)
                                ) {
                                    Text(
                                        text = code.value,
                                        color = Color.White,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }

                            }
                            Row(
                                modifier = Modifier.padding(top = 10.dp)
                            ) {
                                Text(
                                    text = "AMOUNT TO DEPOSIT",
                                    modifier = Modifier.padding(5.dp),
                                    style = MaterialTheme.typography.labelSmall
                                )
                                Row(
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(10.dp))
                                        .background(color = Color.Black)
                                        .padding(5.dp)
                                ) {
                                    Text(
                                        text = amountToDisplay,
                                        color = Color.White,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }

                            }
                        }

                        Text(
                            text = errorMessage.value, color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.labelSmall
                        )

                        OutlinedTextField(
                            value = mpesaMessage.value,
                            textStyle = MaterialTheme.typography.labelSmall,
                            onValueChange = { mpesaMessage.value = it },
                            modifier = Modifier.defaultMinSize(minHeight = 150.dp),
                            readOnly = textFieldReadOnly,
                            label = {
                                Text(text = "M-PESA message", style = MaterialTheme.typography.labelMedium) },
                            isError = mpesaMessageError.value,
                            supportingText = {
                                if (mpesaMessageError.value) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Valid M-PESA message required",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.message_24),
                                    contentDescription = "Message Icon"
                                )
                            })
                    }
                    if (startToSave.value) {
                        ProgressIndicator()
                    }
                }

            },
            confirmButton = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(3.dp),
                    onClick = {
                    saveDetails()
                },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )) {
                    Text(
                        text = "VERIFY",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
            dismissButton = {

            }

        )
    }
}