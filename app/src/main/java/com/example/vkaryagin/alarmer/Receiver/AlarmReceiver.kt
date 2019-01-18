package com.example.vkaryagin.alarmer.Receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.vkaryagin.alarmer.Activity.AlarmActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(this.javaClass.name, "Start.")

        val alarmActivityIntent = Intent(context, AlarmActivity::class.java)
        alarmActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        alarmActivityIntent.putExtras(intent)
        context?.startActivity(alarmActivityIntent)
    }

}