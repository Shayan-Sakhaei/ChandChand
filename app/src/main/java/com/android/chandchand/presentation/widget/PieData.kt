package com.android.chandchand.presentation.widget

import android.graphics.Paint
import com.android.chandchand.presentation.utils.toDpf

class PieData {
    val pieSlices = HashMap<String, PieSlice>()
    var totalValue = 0.0

    fun add(name: String, value: Int, color: Int) {
        if (pieSlices.containsKey(name)) {
            pieSlices[name]?.let { it.value += value }
        } else {
            pieSlices[name] = PieSlice(name, value, 0f, 0f, createPaint(color))
        }
        totalValue += value
    }

    private fun createPaint(color: Int): Paint {
        val newPaint = Paint()
        newPaint.color = color
        newPaint.style = Paint.Style.STROKE
        newPaint.strokeWidth = 6.toDpf()

        newPaint.isAntiAlias = true
        return newPaint
    }
}