package com.android.chandchand.presentation.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.android.chandchand.R
import com.android.chandchand.presentation.utils.toDp
import com.android.chandchand.presentation.utils.toDpf

class LogoPieChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private var data: PieData? = null
    private val outerOvalPaint = Paint()
    private val outerOval = RectF()
    private val innerOval = RectF()

    init {
        setPadding(32.toDp(), 32.toDp(), 32.toDp(), 32.toDp())
        outerOvalPaint.apply {
            style = Paint.Style.STROKE
        }
    }

    fun setData(pieData: PieData) {
        data = pieData
        setPieSliceDimensions()
        invalidate()
    }

    private fun setPieSliceDimensions() {
        var lastAngle = -90f
        data?.pieSlices?.forEach {
            it.value.startAngle = lastAngle
            it.value.sweepAngle = (((it.value.value / data?.totalValue!!)) * 360f).toFloat() * -1
            lastAngle += it.value.sweepAngle
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        outerOval.set(0f, 0f, w.toFloat(), h.toFloat())
        outerOval.inset(8.toDpf(), 8.toDpf())
        innerOval.set(outerOval)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        outerOvalPaint.color = ContextCompat.getColor(context, R.color.colorBorder)
        outerOvalPaint.strokeWidth = 16.toDpf()
        canvas?.drawArc(outerOval, 0f, 360f, false, outerOvalPaint)

        outerOvalPaint.color = ContextCompat.getColor(context, R.color.colorBackground)
        outerOvalPaint.strokeWidth = 14.toDpf()
        canvas?.drawArc(outerOval, 0f, 360f, false, outerOvalPaint)

        data?.pieSlices?.let { slices ->
            slices.forEach {
                canvas?.drawArc(
                    innerOval,
                    it.value.startAngle,
                    it.value.sweepAngle,
                    false,
                    it.value.paint
                )
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(128.toDp(), MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(128.toDp(), MeasureSpec.EXACTLY)
        )
    }
}