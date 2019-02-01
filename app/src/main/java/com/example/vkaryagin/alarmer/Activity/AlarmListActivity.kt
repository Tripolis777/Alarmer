package com.example.vkaryagin.alarmer.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.vkaryagin.alarmer.Adapter.AlarmListAdapter
import com.example.vkaryagin.alarmer.Database.Helper.ORMLiteDatabaseHelper
import com.example.vkaryagin.alarmer.R
import com.j256.ormlite.android.apptools.OpenHelperManager
import java.lang.AssertionError

class AlarmListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var _databaseHelper: ORMLiteDatabaseHelper? = null
    private val databaseHelper: ORMLiteDatabaseHelper
        get() {
            if (_databaseHelper == null) {
                _databaseHelper = OpenHelperManager.getHelper(this, ORMLiteDatabaseHelper::class.java)
            }
            return _databaseHelper ?: throw AssertionError("Database Helper is null")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_list)

        Log.e(this.javaClass.name, "onCreate activity")
        val testDataset = databaseHelper.alarmRuntimeDao.queryForAll()

        viewManager = LinearLayoutManager(this)
        viewAdapter = AlarmListAdapter(testDataset)
        recyclerView = findViewById<RecyclerView>(R.id.alarm_list_recycle).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onPause() {
        super.onPause()
        Log.w(this.localClassName, "onPause()")
    }

    override fun onResume() {
        super.onResume()
        Log.w(this.localClassName, "onResume()")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper()
            _databaseHelper = null
        }
    }

//    fun openMusicLibrary() {
//        var openAudioIntent = Intent()
//        openAudioIntent.setType("audio/*")
//        openAudioIntent.setAction(Intent.ACTION_GET_CONTENT)
//        startActivityForResult(openAudioIntent, REQUEST_CODE_BROWSE_AUDIO)
//    }
}
