package com.example.cdcapplication.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Arch(){
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(Brush.verticalGradient(
                colors = listOf(
                    Color(0xff248801),
                    Color(0xff136527)
                )
            )))
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        {
            drawArc(
                color = Color(0xff136527),
                startAngle = 0F,
                sweepAngle = 180F,
                useCenter = true,
                topLeft = Offset(
                    x = 0.dp.toPx(),
                    y = -30.dp.toPx()
                )
            )
        }
    }
}