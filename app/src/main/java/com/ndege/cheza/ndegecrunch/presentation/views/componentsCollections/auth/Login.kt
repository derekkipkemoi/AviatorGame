package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ndege.cheza.ndegecrunch.presentation.components.AuthHeader
import com.ndege.cheza.ndegecrunch.presentation.components.ProgressIndicator
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.util.regex.Pattern
import kotlin.time.Duration.Companion.seconds

@Composable
fun Login(
    preferenceDataStoreViewModel: PreferenceDataStoreViewModel = hiltViewModel(),
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel()
) {
    val state by getNdegeCrashDataViewModel.state.collectAsState()
    val registeredPhone by preferenceDataStoreViewModel.getPhoneNumber().collectAsState("")
    val registeredPassword by preferenceDataStoreViewModel.getPassword().collectAsState("")


    var registeredPhoneNumberError by remember {
        mutableStateOf(false)
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }
    var phoneNumberError by remember {
        mutableStateOf(false)
    }

    var password by remember {
        mutableStateOf("")
    }
    var passwordError by remember {
        mutableStateOf(false)
    }

    var loggingIn by remember {
        mutableStateOf(false)
    }

    fun validateFields() {
        val phonePattern = "^[+]?[0-9]{10,13}\$"
        if (!Pattern.compile(phonePattern).matcher(phoneNumber).matches()) {
            phoneNumberError = true
            return
        } else {
            phoneNumberError = false
        }

        if (phoneNumber != registeredPhone) {
            registeredPhoneNumberError = true
            return
        } else {
            registeredPhoneNumberError = false
        }

        if (password != registeredPassword) {
            passwordError = true
            return
        } else {
            passwordError = false
        }

        loggingIn = true
    }

    if (loggingIn) {
        LaunchedEffect(Unit) {
            delay(3.seconds)
            preferenceDataStoreViewModel.setUserLoggedIn(true)
            loggingIn = false
            getNdegeCrashDataViewModel.setShowLoginDialog(false)
        }
    }

    Column {
            OutlinedButton(
                onClick = {
                    getNdegeCrashDataViewModel.setShowLoginDialog(true)
                },
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                ),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.labelSmall
                )

        }

        if (state.showLoginDialog) {
            AlertDialog(modifier = Modifier
                .border(
                width = 0.4.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(3.dp)
            ),
                shape = RoundedCornerShape(3.dp),

                containerColor = MaterialTheme.colorScheme.primary,
                onDismissRequest = { /*TODO*/ },
                text = {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AuthHeader("Welcome Back", showAlertDialog = {
                                getNdegeCrashDataViewModel.setShowLoginDialog(false)
                            })
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp)
                            ) {
                                Text(
                                    text = "Login back into your account",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(bottom = 15.dp)
                                )
                                OutlinedTextField(
                                    value = phoneNumber,
                                    onValueChange = {
                                        phoneNumber = it
                                    },
                                    label = {
                                        Text(
                                            text = "Enter Phone Number",
                                            color = Color.White,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        cursorColor = Color.White,
                                        focusedContainerColor = MaterialTheme.colorScheme.primary,
                                        focusedBorderColor = Color.White,
                                        focusedTextColor = Color.White,
                                        focusedLabelColor = Color.White,
                                        unfocusedTextColor = Color.White
                                    ),

                                    isError = phoneNumberError,
                                    supportingText = {
                                        if (phoneNumberError) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = "Valid phone number required",
                                                color = Color.Red
                                            )
                                        }
                                        if (registeredPhoneNumberError) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = "Correct phone number required",
                                                color = Color.Red
                                            )
                                        }
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Phone,
                                            contentDescription = "Phone Icon"
                                        )
                                    },


                                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                )
                                OutlinedTextField(
                                    value = password,
                                    onValueChange = {
                                        password = it
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        cursorColor = Color.White,
                                        focusedContainerColor = MaterialTheme.colorScheme.primary,
                                        focusedBorderColor = Color.White,
                                        focusedTextColor = Color.White,
                                        focusedLabelColor = Color.White,
                                        unfocusedTextColor = Color.White
                                    ),
                                    label = {
                                        Text(
                                            text = "Enter Password",
                                            color = Color.White,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    },
                                    isError = passwordError,
                                    supportingText = {
                                        if (passwordError) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = "Valid password required",
                                                color = Color.Red
                                            )
                                        }
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = "Password Icon"
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                                )
                            }

                            Button(
                                onClick = {
                                    validateFields()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(3.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = "LOGIN", style = MaterialTheme.typography.labelMedium)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Already have an account ? ",
                                    style = MaterialTheme.typography.labelSmall,
//                                    modifier = Modifier.padding(bottom = 15.dp)
                                )
                                Text(
                                    text = "Register",
                                    modifier = Modifier.clickable {
                                        getNdegeCrashDataViewModel.setShowRegisterDialog(true)
                                        getNdegeCrashDataViewModel.setShowLoginDialog(false)
                                    },
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = MaterialTheme.typography.labelSmall,
//                                    modifier = Modifier.padding(bottom = 15.dp)
                                )
                            }

                        }
                        if (loggingIn) {
                            ProgressIndicator()
                        }
                    }
                },
                confirmButton = { /*TODO*/ })
        }
    }
}