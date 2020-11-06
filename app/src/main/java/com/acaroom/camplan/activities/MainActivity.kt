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

class MainActivity : AppCompatActivity(){

    //Database Initialize for Singleton
    private var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Database Initialize
        database = AppDatabase.getInstance(this)
        //Add Saved Contents
        val savedCampData = database!!.campDataDao().getAll()
        if(savedCampData.isNotEmpty()){
            addDataList.addAll(savedCampData)
        }

        //Connect RecyclerView Adapter
        val adapter = MainContentsRecyclerViewAdapter()
        adapter.submitList(addDataList)
        adapter.notifyDataSetChanged()
        main_contents_recycler_view.adapter = adapter

        main_write_btn.setOnClickListener {
            val intent = Intent(this, AddContentsActivity::class.java)
            startActivity(intent)
        }
    }
}