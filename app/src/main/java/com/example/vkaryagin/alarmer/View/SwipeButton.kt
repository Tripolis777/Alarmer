package com.example.vkaryagin.alarmer.View

import android.animation.*
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.vkaryagin.alarmer.R

class SwipeButton : RelativeLayout {

    private lateinit var slidingButton: ImageView
    private var initialX: Float = 0f
    private var active: Boolean = false
    private var initialButtonWidth: Int = 0
    private lateinit var centerText: TextView

    private var disabledDrawable: Drawable? = null
    private var enabledDrawable: Drawable? = null

    private var mButtonText: String = "SWIPE"

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
        init_attrs(context, attrs)
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
        centerText.text = mButtonText//"SWIPE" //TODO(replace on android res string)
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

        setOnTouchListener(getButtonTouchListener())
    }

    private fun init_attrs(context: Context, attrs : AttributeSet?) {
        var typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SwipeButton, 0, 0)
         try {
            if (typedArray.hasValue(R.styleable.SwipeButton_buttonText))
                mButtonText = typedArray.getString(R.styleable.SwipeButton_buttonText)
         } finally {
             typedArray.recycle()
         }
    }

    private fun getButtonTouchListener() : OnTouchListener {
        return object : OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> return true
                    MotionEvent.ACTION_MOVE -> {
                        if (initialX == 0f) initialX = slidingButton.x

                        if (event.x > initialX + slidingButton.width / 2 &&
                                event.x + slidingButton.width / 2 < width) {
                            slidingButton.x = event.x - slidingButton.width / 2
                            centerText.alpha = 1 - 1.3f * ( slidingButton.x + slidingButton.width ) / width
                        }

                        if (event.x + slidingButton.width / 2 > width &&
                                slidingButton.x + slidingButton.width / 2 < width)
                            slidingButton.x = (width - slidingButton.width).toFloat()

                        if (event.x < slidingButton.width / 2 && slidingButton.x > 0)
                            slidingButton.x = 0f

                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        if (active) {
                            collapseButton()
                        } else {
                            initialButtonWidth = slidingButton.width

                            if (slidingButton.x + slidingButton.width > width * .85f ) {
                                expandButton()
                            } else {
                                moveButtonBack()
                            }
                        }

                        return true
                    }
                    else -> return false
                }
            }
        }
    }

    private fun expandButton() {
        val positionAnimator = ValueAnimator.ofFloat(slidingButton.x, 0f)
        positionAnimator.addUpdateListener {
            slidingButton.x = it.animatedValue as Float
        }

        val widthAnimator = ValueAnimator.ofInt(slidingButton.width, width)
        widthAnimator.addUpdateListener {
            var params = slidingButton.layoutParams
            params.width = it.animatedValue as Int
            slidingButton.layoutParams = params
        }

        var animatorSet = AnimatorSet()
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation : Animator) {
                super.onAnimationEnd(animation)

                active = true
                slidingButton.setImageDrawable(enabledDrawable)

            }
        })

        animatorSet.playTogether(positionAnimator, widthAnimator)
        animatorSet.start()
    }

    private fun collapseButton() {
        val widthAnimator = ValueAnimator.ofInt(slidingButton.width, initialButtonWidth)

        widthAnimator.addUpdateListener {
            var params = slidingButton.layoutParams
            params.width = it.animatedValue as Int
            slidingButton.layoutParams = params
        }

        widthAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)

                active = false
                slidingButton.setImageDrawable(disabledDrawable)
            }
        })

        var objectAnimator = ObjectAnimator.ofFloat(centerText, "alpha", 1f)

        var animatorSet = AnimatorSet()
        animatorSet.playTogether(objectAnimator, widthAnimator)
        animatorSet.start()
    }

    private fun moveButtonBack() {
        val positionAnimator = ValueAnimator.ofFloat(slidingButton.x, 0f)
        positionAnimator.interpolator = AccelerateDecelerateInterpolator()
        positionAnimator.addUpdateListener {
            var x = it.animatedValue as Float
            slidingButton.x = x
        }

        var objectAnimator = ObjectAnimator.ofFloat(centerText, "alpha", 1f)
        positionAnimator.duration = 200

        var animatorSet = AnimatorSet()
        animatorSet.playTogether(objectAnimator, positionAnimator)
        animatorSet.start()
    }
}