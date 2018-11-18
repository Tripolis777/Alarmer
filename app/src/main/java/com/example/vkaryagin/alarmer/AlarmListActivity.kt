package com.example.vkaryagin.alarmer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.vkaryagin.alarmer.Adapter.AlarmListAdapter
import com.example.vkaryagin.alarmer.Core.AlarmItem
import java.util.*

class AlarmListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_list)

        //TODO(delete this code)
        val testDataset = arrayOf(
            AlarmItem("First Alarm", Calendar.getInstance().getTime(), true),
            AlarmItem("Second Alarm",  Calendar.getInstance().getTime(), true)
        )

        viewManager = LinearLayoutManager(this)
        viewAdapter = AlarmListAdapter(testDataset)
        recyclerView = findViewById<RecyclerView>(R.id.alarm_list_recycle).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
