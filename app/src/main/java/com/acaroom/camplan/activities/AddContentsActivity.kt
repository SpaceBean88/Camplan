package com.acaroom.camplan.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.acaroom.camplan.R
import com.acaroom.camplan.utils.Constants.TAG
import kotlinx.android.synthetic.main.activity_add_contents.*

class AddContentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contents)

        //Camping Area Select
       val areaItems = resources.getStringArray(R.array.area_array)
       var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, areaItems)
       area_list_spinner.adapter = adapter
       area_list_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
           override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               //TODO:: DATA class에 데이터 저장
               Log.d(TAG, "onItemSelected - selected Item : ${areaItems.get(position)}")
           }

           override fun onNothingSelected(parent: AdapterView<*>?) {

           }

       }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}