package com.example.vkaryagin.alarmer.Activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.vkaryagin.alarmer.BuildConfig
import com.example.vkaryagin.alarmer.Core.AlarmSharedObject

class AlarmActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.getBundleExtra("bundle")
        val alarmData = if (bundle != null) bundle.getParcelable<AlarmSharedObject>("alarm_data") else AlarmSharedObject()
        if (alarmData == null)
            Log.e(this.localClassName, "Alarm data id null")

        Log.e(this.localClassName, "Activity is onCreate()")
        if (BuildConfig.DEBUG) Log.e(this.localClassName, "Alarm name is ${alarmData.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}