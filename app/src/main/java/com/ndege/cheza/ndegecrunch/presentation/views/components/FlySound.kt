package com.ndege.cheza.ndegecrunch.presentation.views.components

import android.media.MediaPlayer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.ndege.cheza.ndegecrunch.R

@Composable
fun FlySound(playingTimer: Double) {
    var mMediaPlayer: MediaPlayer? = null
    val context = LocalContext.current

    Text(text = playingTimer.toString(),
        color = Color.White)

    if (playingTimer > 0.00) {
        mMediaPlayer = MediaPlayer.create(context, R.raw.plane_sound)
        mMediaPlayer!!.isLooping = true
        mMediaPlayer.start()
    }

    if (playingTimer <= 0.00) {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop()
            mMediaPlayer.release()
            mMediaPlayer = null
        }
    }

}