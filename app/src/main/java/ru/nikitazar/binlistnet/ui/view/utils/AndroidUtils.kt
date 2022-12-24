package ru.nikitazar.binlistnet.ui.view.utils

import android.content.Context
import kotlin.math.ceil

object AndroidUtils {

    fun dpToPixel(context: Context, dp: Float): Int =
        ceil(context.resources.displayMetrics.density * dp).toInt()
}