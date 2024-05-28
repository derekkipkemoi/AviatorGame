package com.ndege.cheza.ndegecrunch.presentation.views.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.ndege.cheza.ndegecrunch.R

@Composable
fun WaitingGif(){ 
    Column(modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxWidth().height(150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.load)
                .decoderFactory(
                    if (Build.VERSION.SDK_INT >= 28) {
                        ImageDecoderDecoder.Factory()
                    } else {
                        GifDecoder.Factory()
                    }
                )
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}