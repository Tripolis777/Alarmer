package com.example.vkaryagin.alarmer.Activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.vkaryagin.alarmer.BuildConfig
import com.example.vkaryagin.alarmer.Core.AlarmSharedObject
import com.example.vkaryagin.alarmer.R

class AlarmActivity : Activity() {

    //private lateinit var buttonClose : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val bundle = intent.getBundleExtra("bundle")
        val alarmData = if (bundle != null) bundle.getParcelable<AlarmSharedObject>("alarm_data") else AlarmSharedObject()
        if (alarmData == null)
            Log.e(this.localClassName, "Alarm data id null")

        Log.e(this.localClassName, "Activity is onCreate()")
        if (BuildConfig.DEBUG) Log.e(this.localClassName, "Alarm name is ${alarmData.name}")

        //buttonClose = findViewById(R.id.buttonClose) as Button
//        buttonClose.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(view: View?) {
//                this@AlarmActivity.finish()
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}