package com.ndege.cheza.ndegecrunch.presentation.views.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.ndege.cheza.ndegecrunch.R

@Composable
fun Anime(
) {
   AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.fly)
            .decoderFactory(
                if (SDK_INT >= 28) {
                    ImageDecoderDecoder.Factory()
                } else {
                    GifDecoder.Factory()
                }
            )
            .build(),
        contentDescription = null,
       modifier = Modifier.background(Color.Black)
           .fillMaxWidth()
           .height(200.dp)
    )
}