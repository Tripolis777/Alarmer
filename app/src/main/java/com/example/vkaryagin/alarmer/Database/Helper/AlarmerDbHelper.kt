package com.example.vkaryagin.alarmer.Database.Helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.vkaryagin.alarmer.Database.Schema.Alarm

class AlarmerDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

   override fun onCreate(db: SQLiteDatabase?) {
       db?.execSQL(Alarm.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
    const val DATABASE_NAME = "Alarmer.db"
    const val DATABASE_VERSION = 1
}
}