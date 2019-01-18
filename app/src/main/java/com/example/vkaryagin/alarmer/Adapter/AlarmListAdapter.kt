package com.example.vkaryagin.alarmer.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import com.example.vkaryagin.alarmer.Database.Schema.AlarmTable
import com.example.vkaryagin.alarmer.Listener.OnChecked.AlarmSwitch
import com.example.vkaryagin.alarmer.R

class AlarmListAdapter(private val dataset: List<AlarmTable>) : RecyclerView.Adapter<AlarmListAdapter.AlarmViewHolder>() {

    class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView = itemView.findViewById(R.id.alarm_name) as TextView
        val timeView = itemView.findViewById(R.id.alarm_time) as TextView
        val enabledView = itemView.findViewById(R.id.alarm_switch) as Switch

        var dataItem : AlarmTable? = null

        init {
            enabledView.setOnCheckedChangeListener(AlarmSwitch(this))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.alarm_item_layout, parent, false)
        return AlarmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val value = dataset[position]

        holder.dataItem = value
        holder.nameView.text = value.name
        holder.timeView.text = "${value.hours}:${value.minutes}"
        holder.enabledView.isChecked = value.enabled
    }

    override fun getItemCount() = dataset.size
}