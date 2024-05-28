package com.ndege.cheza.ndegecrunch.presentation.views.entryPoints

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.ndege.cheza.ndegecrunch.presentation.views.navigation.NavigationSetUp
import com.ndege.cheza.ndegecrunch.presentation.views.ui.theme.NdegeCrunchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NdegeCrunchTheme {
                Surface {
                    val navHostController = rememberNavController()
                    NavigationSetUp(navHostController)
                }
            }
        }
    }
}



