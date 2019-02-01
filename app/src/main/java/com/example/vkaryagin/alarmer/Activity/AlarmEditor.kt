package com.example.vkaryagin.alarmer.Activity

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.*
import com.example.vkaryagin.alarmer.Core.AlarmSharedObject
import com.example.vkaryagin.alarmer.R

class AlarmEditor : Activity() {

    companion object {
        private const val REQUEST_CODE_BROWSE_AUDIO = 1;
    }

    private lateinit var timePicker: TimePicker
    private lateinit var btnSave : Button
    private lateinit var etAlarmName : EditText
    private lateinit var soundPicker : LinearLayout

    private lateinit var twSoundName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_editor)

        timePicker = findViewById(R.id.timePicker)
        timePicker.setIs24HourView(true)

        soundPicker = findViewById(R.id.soundPicker)
        twSoundName = findViewById(R.id.twSoundDescription)
        etAlarmName = findViewById(R.id.etAlarmName)
        btnSave = findViewById(R.id.btnSave)

        var alarmInfo : AlarmSharedObject? = intent.getParcelableExtra<AlarmSharedObject>("alarm_info")
        if (alarmInfo != null) {
            etAlarmName.setText(alarmInfo.name)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.hour = alarmInfo.hours
                timePicker.minute = alarmInfo.minutes
            } else {
                timePicker.currentHour = alarmInfo.hours
                timePicker.currentMinute = alarmInfo.minutes
            }
        }

        soundPicker.setOnClickListener {
            var openAudioIntent = Intent()
            openAudioIntent.setType("audio/*")
            openAudioIntent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(openAudioIntent, REQUEST_CODE_BROWSE_AUDIO)
        }

        btnSave.setOnClickListener { finish() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        if (requestCode != REQUEST_CODE_BROWSE_AUDIO || resultCode != RESULT_OK) return

        var fileUri = data.data
        Log.e(this.localClassName, "Uri: $fileUri")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}