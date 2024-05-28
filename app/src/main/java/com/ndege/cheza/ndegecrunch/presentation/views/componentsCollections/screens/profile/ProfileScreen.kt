package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.media3.common.util.Util
import androidx.navigation.NavController
import com.ndege.cheza.ndegecrunch.R
import com.ndege.cheza.ndegecrunch.presentation.components.LogOutCard
import com.ndege.cheza.ndegecrunch.presentation.components.ProgressIndicator
import com.ndege.cheza.ndegecrunch.presentation.components.RateApp
import com.ndege.cheza.ndegecrunch.presentation.components.ScreensHeader
import com.ndege.cheza.ndegecrunch.presentation.views.navigation.NavigationRoutes
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel
import com.ndege.cheza.ndegecrunch.utils.Utils
import kotlinx.coroutines.delay
import java.text.DecimalFormat
import kotlin.time.Duration.Companion.seconds

@Composable
fun ProfileScreen(
    navController: NavController,
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel(),
    preferenceDataStoreViewModel: PreferenceDataStoreViewModel = hiltViewModel()
) {
    val state by getNdegeCrashDataViewModel.state.collectAsState()
    val showAnimation by preferenceDataStoreViewModel.getShowAnimation()
        .collectAsState(initial = false)
    val accountBalance by preferenceDataStoreViewModel.getAccountBalance().collectAsState(0.00)
    val phoneNumber by preferenceDataStoreViewModel.getPhoneNumber().collectAsState("String")
    val df = DecimalFormat("0.00")
    var showProgress by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    var rateApp by remember {
        mutableStateOf(false)
    }
    var shareApp by remember {
        mutableStateOf(false)
    }

    if (rateApp){
        RateApp(context)
    }

    if (shareApp){
        Utils.shareAppPlayStoreUrl(context)
    }

    fun showProgressIndicator() {
        showProgress = true
    }

    if (showProgress) {
        LaunchedEffect(Unit) {
            delay(2.seconds)
            showProgress = false
            preferenceDataStoreViewModel.setUserLoggedIn(false)
            navController.popBackStack()
        }
    }

    var playSound by remember {
        mutableStateOf(false)
    }


    var switchTheme by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxSize()
            ) {
                ScreensHeader(title = "Account", navController)
            }
            Column(
                modifier = Modifier
                    .weight(9F)
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .border(
                            width = 0.4.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .background(
                            color = Color.Black, shape = RoundedCornerShape(3.dp)
                        )
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.manage_accounts_24),
                            contentDescription = "",
                            modifier = Modifier
                                .width(28.dp)
                                .height(26.dp)
                        )
                        Text(
                            text = "ACCOUNT",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color.White)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Phone Number :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Text(
                            text = phoneNumber,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Account Balance :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Text(
                            text = df.format(accountBalance) + " KES",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.transaction),
                            contentDescription = "",
                            modifier = Modifier
                                .width(28.dp)
                                .height(26.dp)
                        )
                        Text(
                            text = "TRANSACT",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color.White)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(NavigationRoutes.WithdrawCash.routeName)
                            },
                            shape = RoundedCornerShape(3.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "WITHDRAW"
                            )
                        }
                        Button(
                            onClick = {
                                navController.navigate(NavigationRoutes.DepositCash.routeName)
                            },
                            shape = RoundedCornerShape(3.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onTertiary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "DEPOSIT"
                            )
                        }

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.settings_24),
                            contentDescription = "",
                            modifier = Modifier
                                .width(28.dp)
                                .height(26.dp)
                        )
                        Text(
                            text = "SETTINGS",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color.White)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Show Animations :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Switch(
                            checked = showAnimation,
                            onCheckedChange = {
                                preferenceDataStoreViewModel.setShowAnimation(!showAnimation)
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                                checkedBorderColor = Color.White,
                                uncheckedBorderColor = Color.White,
                                uncheckedThumbColor = MaterialTheme.colorScheme.secondary
                            ),
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Demo Account :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Switch(
                            checked = !state.enableDemoAccount,
                            onCheckedChange = {
                                getNdegeCrashDataViewModel.enableDemoAccount(!state.enableDemoAccount)
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                                checkedBorderColor = Color.White,
                                uncheckedBorderColor = Color.White,
                                uncheckedThumbColor = MaterialTheme.colorScheme.secondary
                            ),
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Play Sound :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Switch(
                            checked = playSound,
                            onCheckedChange = {
                                playSound = !playSound
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                                checkedBorderColor = Color.White,
                                uncheckedBorderColor = Color.White,
                                uncheckedThumbColor = MaterialTheme.colorScheme.secondary
                            ),
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Dark Theme :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Switch(
                            checked = switchTheme,
                            onCheckedChange = {
                                switchTheme = !switchTheme
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                                checkedBorderColor = Color.White,
                                uncheckedBorderColor = Color.White,
                                uncheckedThumbColor = MaterialTheme.colorScheme.secondary
                            ),
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.more_24),
                            contentDescription = "",
                            modifier = Modifier
                                .width(28.dp)
                                .height(26.dp)
                        )
                        Text(
                            text = "MORE",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color.White)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Share :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.share_24),
                            contentDescription = "",
                            modifier = Modifier
                                .width(28.dp)
                                .height(26.dp)
                                .clickable {
                                    shareApp = true
                                }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Rate :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.rate_24),
                            contentDescription = "",
                            modifier = Modifier
                                .width(28.dp)
                                .height(26.dp)
                                .clickable {
                                    rateApp = true
                                }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Privacy Policy :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.policy_24),
                            contentDescription = "",
                            modifier = Modifier
                                .width(28.dp)
                                .height(26.dp)
                                .clickable {
                                    navController.navigate(NavigationRoutes.PrivacyPolicy.routeName)
                                }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Logout  :",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        LogOutCard(showProgressIndicator = { showProgressIndicator() })

                    }
                }
            }
        }
        if (showProgress) {
            ProgressIndicator()
        }
    }
}