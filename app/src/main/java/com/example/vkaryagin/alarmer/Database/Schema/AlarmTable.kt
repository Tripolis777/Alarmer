package com.example.vkaryagin.alarmer.Database.Schema

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.*

@DatabaseTable(tableName = "alarm")
class AlarmTable {
    constructor() {
    }

    constructor(name: String, time: Calendar, enabled: Boolean) {
        this.name = name
        this.hours = time.get(Calendar.HOUR)
        this.minutes = time.get(Calendar.MINUTE)
        this.enabled = enabled
    }

    @DatabaseField(generatedId = true)
    private val id: Int? = null

    @DatabaseField
    var name: String? = null

    @DatabaseField
    var hours : Int = 0

    @DatabaseField
    var minutes: Int = 0

    @DatabaseField
    var enabled: Boolean = false


}