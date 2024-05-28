package com.ndege.cheza.ndegecrunch.presentation.views.components

import android.content.Context
import androidx.compose.runtime.Composable
import com.google.android.play.core.review.ReviewException
import com.google.android.play.core.review.ReviewManagerFactory

@Composable
fun RateApp(context: Context){
    val manager = ReviewManagerFactory.create(context)
    val request = manager.requestReviewFlow()
    request.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val reviewInfo = task.result
        } else {
            // There was some problem, log or handle the error code.
            val reviewErrorCode = (task.exception as ReviewException).errorCode
        }
    }
}