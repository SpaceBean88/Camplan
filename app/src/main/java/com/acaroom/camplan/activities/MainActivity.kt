package com.acaroom.camplan.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.acaroom.camplan.R
import com.acaroom.camplan.data.CampData
import com.acaroom.camplan.model.CampViewModel
import com.acaroom.camplan.recyclerview.CampRecyclerViewInterface
import com.acaroom.camplan.recyclerview.MainContentsRecyclerViewAdapter
import com.acaroom.camplan.utils.Constants.TAG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content_item.*

class MainActivity : AppCompatActivity() {
    //companion object => java의 static과 유사
    companion object {
        const val REQUEST_CODE = 1
    }

    private lateinit var campViewModel: CampViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recycler View Initialize
        val adapter = MainContentsRecyclerViewAdapter(this)
        main_contents_recycler_view.adapter = adapter
        main_contents_recycler_view.layoutManager = LinearLayoutManager(this)

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

        //Add Contents
        main_write_btn.setOnClickListener {
            val intent = Intent(this, AddContentsActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }//end OnCreate

    //onActivityResult : main 액티비티에서 sub 액티비티를 호출하여 넘어갔다가,
    //다시 main 액티비티로 돌아올 때 사용되는 기본 메소드
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
                Log.d(TAG, "MainActivity - addCampData : $addCampData")
            }
        }
    } //end onActivityResult

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}