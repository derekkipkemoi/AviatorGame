package com.ndege.cheza.ndegecrunch.presentation.componentsCollections

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.ndege.cheza.ndegecrunch.presentation.components.ProgressIndicator
import com.ndege.cheza.ndegecrunch.utils.Constants
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Game(
) {
    var progress by rememberSaveable {
        mutableStateOf(false)
    }
    if (progress){
        LaunchedEffect(Unit) {
            delay(2.seconds)
            progress = false
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
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
                webView.loadUrl(Constants.GAME_URL)
            }
        )
        if (progress){
            ProgressIndicator()
        }
    }

}
