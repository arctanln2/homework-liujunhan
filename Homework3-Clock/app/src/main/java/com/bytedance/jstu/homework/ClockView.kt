package com.bytedance.jstu.homework
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import java.util.*

class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mWidth: Int = 0
    private var mHeight:Int = 0
    private var outCirclePaint: Paint = Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private var degreePaint: Paint = Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private var textPaint: Paint = Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private var hourPaint: Paint = Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private var minPaint: Paint = Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private var sPaint: Paint = Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private var rectPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mcount: Int = 0
    var hcount: Int = 0
    var scount: Int = 0
    var bounds: Rect = Rect()

    init {
        setOnClickListener {
            ++ scount
            invalidate()
        }
        outCirclePaint.strokeWidth = 10f
        outCirclePaint.isAntiAlias = true
        outCirclePaint.style = Paint.Style.STROKE

//        degreePaint = Paint()
        degreePaint.strokeWidth = 10f

//        hourPaint = Paint()
        hourPaint.setStrokeWidth(25f)

//        minPaint = Paint()
        minPaint.setStrokeWidth(15f)

//        sPaint = Paint()
        sPaint.setStrokeWidth(10f)
        rectPaint.color = Color.YELLOW
        rectPaint.alpha = 70
        textPaint.color = Color.LTGRAY
        textPaint.textSize = 120f


    }
    private fun getContentSize(content: String): Pair<Int, Int> {

        textPaint.getTextBounds(content, 0, content.length, bounds)
        val textWith = bounds.width()
        val textHeight = bounds.height()
        return textWith to textHeight
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val dEdge = 30f

        canvas?.drawCircle(width.toFloat()/2,width.toFloat()/2,width.toFloat()/3,outCirclePaint)

        // 绘制时针
        mWidth=width
        mHeight=width
        var degreeLen=60f
        for (i in 0..59) {
            degreeLen = if (i % 5 ==0){
                60f
            } else {
                30f
            }
            degreePaint.strokeWidth = 10f
            degreePaint.textSize = 60f
            canvas?.drawLine(
                (mWidth / 2).toFloat(),
                (mHeight / 2 - mWidth / 3).toFloat(),
                (mWidth / 2).toFloat(), (mHeight / 2 - mWidth / 3 + degreeLen).toFloat(), degreePaint
            )
            val degree = i.toString()
//                canvas?.drawText(
//                    degree,
//                    mWidth / 2 - degreePaint.measureText(degree) / 2,
//                    (mHeight / 2 - mWidth / 2 + 60).toFloat(),
//                    degreePaint)
            canvas?.rotate(6f, (mWidth / 2).toFloat(), (mHeight / 2).toFloat())
        }

        canvas?.save()

        val calendar: Calendar = Calendar.getInstance()
        hcount = calendar.get(Calendar.HOUR_OF_DAY)
        mcount = calendar.get(Calendar.MINUTE)
        scount = calendar.get(Calendar.SECOND)

        val hLen = 120f
        val mLen = 240f
        val sLen = 360f
        val hx = (hLen * Math.cos(Math.PI * (hcount % 12 - 15) / 6))
        val hy = (hLen * Math.sin(Math.PI * (hcount % 12 - 15) / 6))
        val mx = (mLen * Math.cos(Math.PI * (mcount - 15) / 30))
        val my = (mLen * Math.sin(Math.PI * (mcount - 15) / 30))
        val sx =
            (sLen * Math.cos(Math.PI * (scount - 15) / 30))// -15 是为了调整时差(角度差)

        val sy = (sLen * Math.sin(Math.PI * (scount - 15) / 30))

        canvas?.translate(mWidth.toFloat()/2,mHeight.toFloat()/2);
        canvas?.drawLine(0f,0f,hx.toFloat(),hy.toFloat(),hourPaint);
        canvas?.drawLine(0f,0f,mx.toFloat(),my.toFloat(),minPaint);
        canvas?.drawLine(0f,0f,sx.toFloat(),sy.toFloat(),sPaint);
        canvas?.translate(- mWidth.toFloat()/2,- mHeight.toFloat()/2);

        val timeString = "$hcount:$mcount:$scount"
        val (textWidth, textHeight) = getContentSize(timeString)
        canvas?.drawRect(width /2f-textWidth/2f -dEdge, height * 3f / 4f + dEdge,
            width /2f + textWidth/2f + dEdge, height * 3f / 4f -  textPaint.textSize - dEdge,
            rectPaint)
        canvas?.drawText(timeString, width /2f-textWidth/2f, height * 3f / 4f, textPaint)
        postInvalidateDelayed(1000)
    }
}