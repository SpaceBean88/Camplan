package com.acaroom.camplan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.acaroom.camplan.R
import com.acaroom.camplan.data.AppDatabase
import com.acaroom.camplan.data.CampData
import com.acaroom.camplan.recyclerview.MainContentsRecyclerViewAdapter
import com.acaroom.camplan.utils.Constants
import com.acaroom.camplan.utils.Constants.TAG
import com.acaroom.camplan.utils.DataList.addDataList
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(){

    //Database Initialize for Singleton
    private var database: AppDatabase? = null
    private var campDataList = ArrayList<CampData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //서브Thread 를 사용하여 메인 Thread 에 영향을 주지 않도록 한다.
        database = AppDatabase.getInstance(this)
        val adapter = MainContentsRecyclerViewAdapter(this, campDataList)
        val r = Runnable {
            try {
                //데이터를 읽고쓰는 쓰레드
                campDataList = database?.campDataDao()?.getAll()!!
                adapter.notifyDataSetChanged()
                main_contents_recycler_view.adapter = adapter
                main_contents_recycler_view.layoutManager = LinearLayoutManager(this)
            }catch (e: Exception) {
                Log.d(TAG, "Error - $e")
            }
        }
        val thread = Thread(r)
        thread.start()

        main_write_btn.setOnClickListener {
            val intent = Intent(this, AddContentsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        database = null
        super.onDestroy()
    }
}