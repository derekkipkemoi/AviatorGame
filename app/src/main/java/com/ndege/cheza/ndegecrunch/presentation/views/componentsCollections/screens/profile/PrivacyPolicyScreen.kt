package com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.profile

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.ndege.cheza.ndegecrunch.presentation.components.ProgressIndicator
import com.ndege.cheza.ndegecrunch.presentation.components.ScreensHeader
import com.ndege.cheza.ndegecrunch.utils.Constants
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun PrivacyPolicyScreen(
    navController: NavController
){
    var progress by rememberSaveable {
        mutableStateOf(false)
    }
    if (progress){
        LaunchedEffect(Unit) {
            delay(2.seconds)
            progress = false
        }
    }
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
            ScreensHeader(title = "Privacy Policy", navController)
        }
        Box(
            modifier = Modifier.weight(1F).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                    }
                },
                update = { webView ->
                    progress = true
                    webView.loadUrl(Constants.PRIVACY_POLICY_URL)
                }
            )
            if (progress) {
                ProgressIndicator()
            }
        }
    }
}