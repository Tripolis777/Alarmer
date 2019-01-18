package com.example.vkaryagin.alarmer.Listener.OnChecked

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import com.example.vkaryagin.alarmer.Adapter.AlarmListAdapter
import com.example.vkaryagin.alarmer.Core.AlarmSharedObject
import com.example.vkaryagin.alarmer.Receiver.AlarmReceiver
import java.util.*

class AlarmSwitch(private val viewHolder: AlarmListAdapter.AlarmViewHolder) : CompoundButton.OnCheckedChangeListener {

    override fun onCheckedChanged(switch: CompoundButton?, isChecked: Boolean) {
        if (!isChecked) {
            Toast.makeText(switch?.context, "Switch disabled", Toast.LENGTH_SHORT).show()
            return
        }

        val alarmItem = viewHolder.dataItem!!
        var bundle = Bundle()
        bundle.putParcelable("alarm_data", AlarmSharedObject(alarmItem))

        var alarmIntent = Intent(switch?.context, AlarmReceiver::class.java)
        alarmIntent.putExtra("bundle", bundle)

        var calendar = GregorianCalendar()
        //calendar.set(Calendar.DATE, System.currentTimeMillis())
//        calendar.set(Calendar.HOUR, alarmItem.hours)
//        calendar.set(Calendar.MINUTE, alarmItem.minutes)
        calendar.add(Calendar.SECOND, 5)

        val alarmManager = switch?.context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val millis = System.currentTimeMillis()

        Log.d(this.javaClass.name, "Calendar millis: ${calendar.timeInMillis}")
        Log.d(this.javaClass.name, "System millis: ${millis}")

        Log.w(this.javaClass.name, "Is calendar millis compare system? ${millis.compareTo(calendar.timeInMillis)}")
        alarmManager.setExact(AlarmManager.RTC, calendar.timeInMillis, PendingIntent.getBroadcast(switch?.context, 0, alarmIntent, 0))

        Toast.makeText(switch?.context, "Switch to ${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}:${calendar.get(Calendar.SECOND)} " +
                "as ${alarmItem.hours}:${alarmItem.minutes}", Toast.LENGTH_SHORT).show()
    }
}