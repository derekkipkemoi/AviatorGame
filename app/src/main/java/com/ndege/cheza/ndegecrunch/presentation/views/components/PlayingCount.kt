package com.ndege.cheza.ndegecrunch.presentation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun PlayingCount(
    counterValue: Int, decimalValue: Int
) {
    var counter by remember {
        mutableDoubleStateOf(0.00)
    }
    val df = DecimalFormat("0.00")
    val num = decimalValue.toBigDecimal().divide(100.toBigDecimal(), 2, RoundingMode.HALF_UP)
    counter = counterValue + num.toDouble()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp)
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            color = Color.Transparent, // This avoids the color to overlap with the background
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0x2DFFFFFF), // Starting color of the gradient
                            Color(0x20FFFFFF) // Ending color of the gradient
                        )
                    ),
                    shape = RoundedCornerShape(3.dp) // Fancy rounded corners
                )
                .padding(start = 20.dp, end = 20.dp) // Add padding after background for proper gradient inclusion
        ){

        Text(
            text = df.format(counter) + " x", color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
        }
    }

}