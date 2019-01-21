package com.example.vkaryagin.alarmer.View

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.vkaryagin.alarmer.R

class SwipeButton : RelativeLayout {

    private lateinit var slidingButton: ImageView
    private var initialX: Float = 0.0f
    private var active: Boolean = false
    private var initialButtonWidth: Int = 0
    private lateinit var centerText: TextView

    private var disabledDrawable: Drawable? = null
    private var enabledDrawable: Drawable? = null

    constructor(context: Context) : super(context) {
        init(context, null, -1, -1)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, -1, -1)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr, -1)
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val background = RelativeLayout(context)

        var layoutParamsView = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        layoutParamsView.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        background.background = ContextCompat.getDrawable(context, R.drawable.shape_rounded)

        addView(background, layoutParamsView)

        centerText = TextView(context)

        var layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        centerText.text = "SWIPE" //TODO(replace on android res string)
        centerText.setTextColor(Color.WHITE)
        centerText.setPadding(35, 35, 35, 35)
        background.addView(centerText, layoutParams)

        slidingButton = ImageView(context)

        disabledDrawable = ContextCompat.getDrawable(context, R.drawable.ic_lock_open_black_24dp)
        enabledDrawable = ContextCompat.getDrawable(context, R.drawable.ic_lock_outline_black_24dp)

        slidingButton.setImageDrawable(disabledDrawable)
        slidingButton.setPadding(40, 40, 40, 40)

        val layoutParamsButton = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParamsButton.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)
        layoutParamsButton.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
        slidingButton.background = ContextCompat.getDrawable(context, R.drawable.shape_button)
        slidingButton.setImageDrawable(disabledDrawable)
        addView(slidingButton, layoutParamsButton)

        //setOnTouchListener()
    }
}