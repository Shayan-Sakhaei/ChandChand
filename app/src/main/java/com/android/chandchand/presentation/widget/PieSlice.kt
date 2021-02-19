package com.android.chandchand.presentation.widget

import android.graphics.Paint

data class PieSlice(
    val name: String,
    var value: Int,
    var startAngle: Float,
    var sweepAngle: Float,
    val paint: Paint
)