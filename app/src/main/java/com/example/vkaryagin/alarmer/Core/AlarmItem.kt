package com.example.vkaryagin.alarmer.Core

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AlarmItem(val name: String, time: Date, val enabled: Boolean) {

    companion object {
        @JvmStatic val dateFormat: DateFormat = SimpleDateFormat("HH:mm")
    }

    val time: String = dateFormat.format(time)

}