package com.acaroom.camplan.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.acaroom.camplan.R
import com.acaroom.camplan.data.CampData
import com.acaroom.camplan.model.CampViewModel
import com.acaroom.camplan.recyclerview.MainContentsRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    companion object {
        val REQUEST_CODE = 1
    }

    private lateinit var campViewModel: CampViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recycler View Initialize
        val adapter = MainContentsRecyclerViewAdapter(this)
        main_contents_recycler_view.adapter = adapter
        main_contents_recycler_view.layoutManager = LinearLayoutManager(this)
//        main_contents_recycler_view.addItemDecoration(
//            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        )

        //ViewModel Initialize
        campViewModel = ViewModelProvider(this, CampViewModel.Factory(application))
                            .get(CampViewModel::class.java)
        campViewModel
            .getAll()
            .observe(
                this,
                Observer<List<CampData>> { contentsList ->
            adapter.setList(contentsList)
        })

        main_write_btn.setOnClickListener {
            val intent = Intent(this, AddContentsActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }//end OnCreate

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE) {
            if(resultCode != RESULT_OK) {
                Toast
                    .makeText(
                        this,
                        "Data is not saved because it is Empty", Toast.LENGTH_SHORT
                    )
                    .show()
            } else {
                val addCampData = data!!.getSerializableExtra("camp_data")
                campViewModel.insert(addCampData as CampData)
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}