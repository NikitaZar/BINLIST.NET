package ru.nikitazar.binlistnet.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import ru.nikitazar.binlistnet.R
import ru.nikitazar.binlistnet.ui.view.utils.AndroidUtils

class LogoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {
    private var lineWidth = AndroidUtils.dpToPixel(context, 0.7F).toFloat()
    private var fontSize = AndroidUtils.dpToPixel(context, 20F).toFloat()
    private var center = PointF()
    private val paintLine = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = lineWidth
    }

    private val paintText = Paint().apply {
        color = Color.BLACK
        textSize = fontSize
        textAlign = Paint.Align.LEFT
        typeface = ResourcesCompat.getFont(context, R.font.pfagoraslabpro_bold)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        center = PointF(w / 2F, h / 2F)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val text = context.getString(R.string.app_name).uppercase()
        val textWidth = paintText.measureText(text)
        val textAscent = paintText.ascent()

        val path1 = Path().apply {
            val x = center.x - textWidth / 2 + AndroidUtils.dpToPixel(context, 3F)
            val y = center.y - AndroidUtils.dpToPixel(context, 10F)
            val h = AndroidUtils.dpToPixel(context, 3F)
            val w = AndroidUtils.dpToPixel(context, 30F)
            moveTo(x, y)
            lineTo(x, y - h)
            lineTo(x + w, y - h)
            lineTo(x + w, y)
        }

        val path2 = Path().apply {
            val x = center.x - textWidth / 2 + AndroidUtils.dpToPixel(context, 3F)
            val y = center.y + AndroidUtils.dpToPixel(context, 10F)
            val h = AndroidUtils.dpToPixel(context, 3F)
            val w = AndroidUtils.dpToPixel(context, 30F)
            moveTo(x, y)
            lineTo(x, y + h)
            lineTo(x + w, y + h)
            lineTo(x + w, y)
        }

        canvas.drawText(text, center.x - (textWidth / 2F), center.y - (textAscent / 2F), paintText)
        canvas.drawPath(path1, paintLine)
        canvas.drawPath(path2, paintLine)
    }
}