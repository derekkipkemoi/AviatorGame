package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ndege.cheza.ndegecrunch.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.Util
import androidx.navigation.NavController
import com.ndege.cheza.ndegecrunch.presentation.components.ProgressIndicator
import com.ndege.cheza.ndegecrunch.presentation.views.navigation.NavigationRoutes
import com.ndege.cheza.ndegecrunch.presentation.viewModels.GetNdegeCrashDataViewModel
import com.ndege.cheza.ndegecrunch.utils.Constants
import com.ndege.cheza.ndegecrunch.utils.Utils

@Composable
fun DecisionPoint(
    navController: NavController,
    getNdegeCrashDataViewModel: GetNdegeCrashDataViewModel = hiltViewModel(),

){
    val state by getNdegeCrashDataViewModel.state.collectAsState()
    val context = LocalContext.current
    val networkConnected by remember {
        mutableStateOf(Utils.isNetworkConnected(context))
    }

    var route by remember {
        mutableStateOf(NavigationRoutes.GameScreen.routeName)
    }



    fun navigate(){
        navController.navigate(route)
    }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if (networkConnected){
            state.accountDetails?.showAviator
            state.accountDetails?.let {
                route = if (state.accountDetails!!.showAviator){
                    NavigationRoutes.MainGame.routeName
                }else{
                    NavigationRoutes.GameScreen.routeName
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.icon_), contentDescription = "",
                        modifier = Modifier.width(100.dp))
                    Button(
                        onClick = {
                            navigate()
                        },
                        modifier = Modifier.padding(top = 50.dp),
                        shape = RoundedCornerShape(3.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                    {
                        Text(text = "START GAME")
                    }

                }
            }

            if (state.errorMessage.isNotEmpty()){
                Text(
                    text = state.errorMessage,
                    modifier = Modifier.padding(20.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
                if (state.errorMessage == "Please check your internet connection"){
                    Button(
                        onClick = {
                            Utils.restartApp(context)
                        },
                        shape = RoundedCornerShape(3.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                    {
                        Text(text = "RELOAD")
                    }
                }
            }
            if (state.isLoading){
                ProgressIndicator()
            }
        }else{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Please check your internet connection.",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )
                Button(
                    onClick = {
                        Utils.restartApp(context)
                    },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                {
                    Text(text = "RELOAD")
                }
            }
        }
    }
}