package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ndege.cheza.ndegecrunch.R
import com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.auth.Login
import com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.auth.Register
import com.ndege.cheza.ndegecrunch.presentation.views.navigation.NavigationRoutes
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel
import java.text.DecimalFormat

@Composable
fun TopBar(
    navController: NavController,
    preferenceDataStoreViewModel: PreferenceDataStoreViewModel = hiltViewModel(),
) {
    val loggedIn by preferenceDataStoreViewModel.getUserLoggedIn().collectAsState(false)
    val accountBalance by preferenceDataStoreViewModel.getAccountBalance().collectAsState(0.00)
    val df = DecimalFormat("0.00")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp)
            .height(50.dp)
            .border(
                width = (0.4.dp),
                color = Color.Gray,
            )
            .background(color = Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!loggedIn) {
                Login()
                Spacer(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .height(50.dp)
                        .width(0.5.dp)
                        .background(color = Color.Gray)
                )
                Register()
            }
            if (loggedIn){
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
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (loggedIn) {
                Spacer(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(0.5.dp)
                        .background(color = Color.Gray)
                )
                Text(
                    text = df.format(accountBalance),
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(end = 5.dp, start = 5.dp),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "KES",
                    color = Color.White,
                    modifier = Modifier.padding(end = 15.dp),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(0.5.dp)
                        .background(color = Color.Gray)
                )
                Image(painter = painterResource(id = R.drawable.menu),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .width(28.dp)
                        .height(26.dp)
                        .clickable {
                            navController.navigate(NavigationRoutes.ProfileScreen.routeName)
                        })

            }
        }


    }
}