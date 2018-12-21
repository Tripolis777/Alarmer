package com.example.vkaryagin.alarmer.Database.Schema

import android.provider.BaseColumns

object Alarm {
    object AlarmEntry : BaseColumns {
        const val TABLE_NAME = "alarm"
        const val COLUMN_NAME_ALARM_NAME = "name"
        const val COLUMN_NAME_ALARM_TIME = "time"
        const val COLUMN_NAME_ALARM_ENABLED = "enabled"
    }

    const val SQL_CREATE_ENTRIES = """
            CREATE TABLE ${AlarmEntry.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY,
                ${AlarmEntry.COLUMN_NAME_ALARM_NAME} TEXT,
                ${AlarmEntry.COLUMN_NAME_ALARM_TIME} TEXT,
                ${AlarmEntry.COLUMN_NAME_ALARM_ENABLED} INTEGER)
    """

    private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${AlarmEntry.TABLE_NAME}"
}