package com.fanhl.videoguidedemo.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class OverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val path = Path()
    private var rectF = RectF()

    // 初始化参数
    var overlayColor: Int = 0xCCFFFFFF.toInt() // 默认半透明黑色
        set(value) {
            field = value
            invalidate()
        }
    var hollowRect = RectF(0.2f, 0.2f, 0.8f, 0.3f) // 默认透明区域百分比 (左, 上, 右, 下)
        set(value) {
            field = value
            invalidate()
        }
    var cornerRadius: Float = dpToPx(16f) // 默认圆角大小
        set(value) {
            field = value
            invalidate()
        }

    init {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 设置View的层类型，启用软件或硬件渲染
        setLayerType(LAYER_TYPE_HARDWARE, null)

        paint.color = overlayColor
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

        // 根据百分比计算实际的透明区域
        rectF.left = width * hollowRect.left
        rectF.top = height * hollowRect.top
        rectF.right = width * hollowRect.right
        rectF.bottom = height * hollowRect.bottom

        // 创建圆角矩形路径
        path.reset()
        path.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW)

        // 设置混合模式为CLEAR，使得该区域变为透明
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        canvas.drawPath(path, paint)

        // 恢复混合模式
        paint.xfermode = null
    }

    // dp转px的辅助方法
    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    }
}
