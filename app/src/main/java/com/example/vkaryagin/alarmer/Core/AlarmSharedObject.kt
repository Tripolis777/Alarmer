package com.example.vkaryagin.alarmer.Core

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.example.vkaryagin.alarmer.BuildConfig
import com.example.vkaryagin.alarmer.Database.Schema.AlarmTable

class AlarmSharedObject : Parcelable {
    val hours: Int
    val minutes: Int
    var name: String? = null

    constructor() {
        hours = 0
        minutes = 0
    }

    constructor(tableItem : AlarmTable) {
        hours = tableItem.hours
        minutes = tableItem.minutes

        Log.d(this.javaClass.name, "Create object.")

        if (BuildConfig.DEBUG) {
            name = tableItem.name
        }
    }

    private constructor(parcel: Parcel) {
        hours = parcel.readInt()
        minutes = parcel.readInt()
        Log.e(this.javaClass.name, "Read minutes from parcel: ${ minutes }")

        if (BuildConfig.DEBUG) {
            name = parcel.readString()
            Log.e(this.javaClass.name, "Read name from parcel: ${ if(name != null) name else "undefined" }")
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(hours)
        parcel.writeInt(minutes)

        if (BuildConfig.DEBUG) {
            parcel.writeString(name)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlarmSharedObject> {
        override fun createFromParcel(parcel: Parcel): AlarmSharedObject {
            Log.d("AlarmSharedObject", "Create from Parcel")
            return AlarmSharedObject(parcel)
        }

        override fun newArray(size: Int): Array<AlarmSharedObject?> {
            return arrayOfNulls(size)
        }
    }
}