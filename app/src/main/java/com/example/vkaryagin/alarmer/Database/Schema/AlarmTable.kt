package com.example.vkaryagin.alarmer.Database.Schema

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@DatabaseTable(tableName = "alarm")
class AlarmTable {
    companion object {
        @JvmStatic val dateFormat: DateFormat = SimpleDateFormat("HH:mm")
    }

    constructor() {
    }

    constructor(name: String, time: Date, enabled: Boolean) {
        this.name = name
        this.time =  dateFormat.format(time)
        this.enabled = enabled
    }

    @DatabaseField(generatedId = true)
    private val id: Int? = null

    @DatabaseField
    var name: String? = null

    @DatabaseField
    var time: String? = null

    @DatabaseField
    var enabled: Boolean = false
}