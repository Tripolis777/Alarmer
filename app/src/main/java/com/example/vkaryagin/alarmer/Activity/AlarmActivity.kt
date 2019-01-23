package com.example.vkaryagin.alarmer.Activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.vkaryagin.alarmer.BuildConfig
import com.example.vkaryagin.alarmer.Core.AlarmSharedObject
import com.example.vkaryagin.alarmer.R
import com.example.vkaryagin.alarmer.View.SwipeButton

class AlarmActivity : Activity() {
    private lateinit var btnSwipe: SwipeButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val bundle = intent.getBundleExtra("bundle")
        val alarmData = if (bundle != null) bundle.getParcelable<AlarmSharedObject>("alarm_data") else AlarmSharedObject()
        if (alarmData == null)
            Log.e(this.localClassName, "Alarm data id null")

        Log.e(this.localClassName, "Activity is onCreate()")
        if (BuildConfig.DEBUG) Log.e(this.localClassName, "Alarm name is ${alarmData.name}")

        btnSwipe = findViewById(R.id.swipe_btn)
        btnSwipe.setOnCheckedChangeListener(object : SwipeButton.OnCheckedChangeListener {
            override fun onCheckedChanged(view: SwipeButton, active: Boolean) {
                this@AlarmActivity.finish()
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}