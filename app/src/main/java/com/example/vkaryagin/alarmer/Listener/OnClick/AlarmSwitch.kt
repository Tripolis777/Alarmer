package com.example.vkaryagin.alarmer.Listener.OnClick

import android.view.View
import android.widget.Toast
import com.example.vkaryagin.alarmer.Adapter.AlarmListAdapter

class AlarmSwitch(private val viewHolder: AlarmListAdapter.AlarmViewHolder) : View.OnClickListener {

    override fun onClick(switch: View?) {
        Toast.makeText(switch?.context, "Switch id ${viewHolder?.nameView.text}" , Toast.LENGTH_SHORT).show()
    }
}