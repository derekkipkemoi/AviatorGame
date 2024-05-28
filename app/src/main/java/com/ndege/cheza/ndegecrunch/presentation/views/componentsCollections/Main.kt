package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.play.core.review.ReviewManagerFactory
import com.ndege.cheza.ndegecrunch.presentation.components.RateApp
import com.ndege.cheza.ndegecrunch.presentation.viewModels.PreferenceDataStoreViewModel

@Composable
fun Main(
    navController: NavController
) {
    val context = LocalContext.current
    RateApp(context)
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .fillMaxSize()
    ) {
        TopBar(navController)
        ComponentsCollections()
    }
}