package com.example.vkaryagin.alarmer.Database.Helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.vkaryagin.alarmer.Database.Schema.AlarmTable
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.RuntimeExceptionDao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import java.lang.AssertionError
import java.lang.RuntimeException
import java.sql.SQLException
import java.util.*

class ORMLiteDatabaseHelper(context: Context) : OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "alarmer.db"
        const val DATABASE_VERSION = 1
        private val className = ORMLiteDatabaseHelper::class.simpleName;
    }

    private var _alarmDao: Dao<AlarmTable, Integer>? = null
    private var _alarmRuntimeDao: RuntimeExceptionDao<AlarmTable, Integer>? = null

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        try {
            Log.i(className, "onCreate")
            TableUtils.createTable(connectionSource, AlarmTable::class.java)
        } catch (e : SQLException) {
            Log.e(className, "Can't create database", e)
            throw RuntimeException(e)
        }

        //TODO: replace this code
        val calendar  = GregorianCalendar()
        calendar.set(Calendar.MINUTE, 15)
        calendar.set(Calendar.HOUR, 11)
        alarmRuntimeDao.create(AlarmTable("First Alarm", calendar, false))

        calendar.set(Calendar.MINUTE, 21)
        calendar.set(Calendar.HOUR, 12)
        alarmRuntimeDao.create(AlarmTable("Second Alarm",  calendar, false))

        Log.i(className, "create start entries (test) in onCreate")
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (newVersion > oldVersion) {
            Log.e(className, "database is changed, but onUpdate not implemented")
        }
    }

    override fun close() {
        super.close()
        _alarmDao = null
        _alarmRuntimeDao = null
    }

    val alarmDao: Dao<AlarmTable, Integer>
        get() {
            if (_alarmDao == null) {
                _alarmDao = this.getDao(AlarmTable::class.java)
            }
            return _alarmDao ?: throw AssertionError("Dao is null")
        }

    val alarmRuntimeDao: RuntimeExceptionDao<AlarmTable, Integer>
        get() {
            if (_alarmRuntimeDao == null) {
                _alarmRuntimeDao = getRuntimeExceptionDao(AlarmTable::class.java)
            }
            return _alarmRuntimeDao ?: throw AssertionError("Runtime Dao is null")
        }
}