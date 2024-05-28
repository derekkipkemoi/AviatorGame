package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.auth

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
import kotlinx.coroutines.delay
import java.util.regex.Pattern
import kotlin.time.Duration.Companion.seconds

@Composable
fun Register(
    preferenceDataStoreViewModel: PreferenceDataStoreViewModel = hiltViewModel(),
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel()
) {

    val registeredPhone by preferenceDataStoreViewModel.getPhoneNumber().collectAsState("")

    val state by getNdegeCrashDataViewModel.state.collectAsState()

    var phoneNumber by remember {
        mutableStateOf("")
    }
    var phoneNumberError by remember {
        mutableStateOf(false)
    }

    var registeredPhoneNumberError by remember {
        mutableStateOf(false)
    }

    var password by remember {
        mutableStateOf("")
    }
    var passwordError by remember {
        mutableStateOf(false)
    }

    var passwordConfirm by remember {
        mutableStateOf("")
    }
    var passwordConfirmError by remember {
        mutableStateOf(false)
    }

    var savingData by remember {
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

        if (registeredPhone.isNotEmpty() && phoneNumber == registeredPhone) {
            registeredPhoneNumberError = true
            return
        } else {
            registeredPhoneNumberError = false
        }

        if (password.length < 6) {
            passwordError = true
            return
        } else {
            passwordError = false
        }

        if (passwordConfirm != password) {
            passwordConfirmError = true
            return
        } else {
            passwordConfirmError = false
        }
        savingData = true
    }

    if (savingData) {
        LaunchedEffect(Unit) {
            delay(3.seconds)
            preferenceDataStoreViewModel.savePhoneNumber(phoneNumber)
            preferenceDataStoreViewModel.savePassword(password)
            preferenceDataStoreViewModel.setUserLoggedIn(true)
            savingData = false
            getNdegeCrashDataViewModel.setShowRegisterDialog(false)
        }
    }


        Column {
                Button(
                    onClick = {
                        getNdegeCrashDataViewModel.setShowRegisterDialog(true)
                    },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.labelSmall
                    )
            }

            if (state.showRegisterDialog) {
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
                            AuthHeader("Welcome", showAlertDialog = {
                                getNdegeCrashDataViewModel.setShowRegisterDialog(false)
                            })
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp)
                            ) {
                                Text(
                                    text = "Register into your new account",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(bottom = 15.dp)
                                )
                                OutlinedTextField(
                                    value = phoneNumber,
                                    onValueChange = {
                                        phoneNumber = it
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
                                            text = "Enter Phone Number",
                                            color = Color.White,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    },
                                    isError = phoneNumberError,
                                    supportingText = {
                                        if (phoneNumberError) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = "Valid phone number required",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = Color.Red
                                            )
                                        }
                                        if (registeredPhoneNumberError) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = "Phone number already registered",
                                                style = MaterialTheme.typography.labelSmall,
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
                                                text = "Password too short",
                                                style = MaterialTheme.typography.labelSmall,
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
                                OutlinedTextField(
                                    value = passwordConfirm,
                                    onValueChange = {
                                        passwordConfirm = it
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
                                            text = "Confirm Password",
                                            color = Color.White,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    },
                                    isError = passwordConfirmError,
                                    supportingText = {
                                        if (passwordConfirmError) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = "Passwords should match",
                                                style = MaterialTheme.typography.labelSmall,
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
                                Text(
                                    text = "REGISTER",
                                    style = MaterialTheme.typography.labelMedium
                                )
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
                                    text = "Login",
                                    modifier = Modifier.clickable {
                                        getNdegeCrashDataViewModel.setShowRegisterDialog(false)
                                        getNdegeCrashDataViewModel.setShowLoginDialog(true)
                                    },
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = MaterialTheme.typography.labelSmall,
//                                    modifier = Modifier.padding(bottom = 15.dp)
                                )
                            }

                        }
                            if (savingData) {
                                ProgressIndicator()
                            }
                    }
                    },
                    confirmButton = { /*TODO*/ })
            }
        }
}