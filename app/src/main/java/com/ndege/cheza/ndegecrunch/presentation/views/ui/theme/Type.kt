package com.ndege.cheza.ndegecrunch.presentation.views.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ndege.cheza.ndegecrunch.R

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_light)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 10.sp,
//        letterSpacing = 1.sp
    ), bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_light)),
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 20.sp,
//        letterSpacing = 1.sp
    ), bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_light)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 10.sp,
//        letterSpacing = 1.sp
    ), bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_light)),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 10.sp,
//        letterSpacing = 1.sp
    ), labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_bold)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ), labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_light)),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ), displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.nunito_bold)),
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 8.sp,
        letterSpacing = 0.5.sp
    )
)