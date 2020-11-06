package com.acaroom.camplan.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.acaroom.camplan.R
import com.acaroom.camplan.data.AppDatabase
import com.acaroom.camplan.data.CampData
import com.acaroom.camplan.utils.Constants.TAG
import com.acaroom.camplan.utils.DataList.addDataList
import kotlinx.android.synthetic.main.activity_add_contents.*

class AddContentsActivity : AppCompatActivity() {

    private lateinit var campData: CampData
    private var database: AppDatabase? = null

    private lateinit var addTitle: String
    private lateinit var addCampStartDate: String //TODO:: LocalDate.parse 로 날짜파싱
    private lateinit var addCampEndDate: String //TODO:: LocalDate.parse 로 날짜파싱
    private lateinit var addCampSite: String
    private lateinit var addCampLocation: String
    private lateinit var addCampType: String
    private var addCampBudget: Int = 0

    //Calendar
    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contents)

        Log.d(TAG, "AddContentsActivity - $addDataList")

        start_date_btn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { view, year, month, day ->
                start_date_text.text =
                    year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
                addCampStartDate = start_date_text.text.toString()
            }, year, month, day)
            datePickerDialog.show()
        }

        end_date_btn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { view, year, month, day ->
                end_date_text.text =
                    year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
                addCampEndDate = end_date_text.text.toString()
            }, year, month, day)
            datePickerDialog.show()
        }

        //Camping Area Select
        val areaItems = resources.getStringArray(R.array.area_array)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, areaItems)
        area_list_spinner.adapter = adapter
        area_list_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                addCampLocation = areaItems.get(position)
                Log.d(TAG, "onItemSelected - selected Item : $addCampLocation")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //Camping Div RadioBtn
        camp_div_radio_group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.auto_camping_radio_btn -> {
                    Log.d(TAG, "AddContentsActivity - 오토캠핑 버튼 클릭")
                    addCampType = auto_camping_radio_btn.text.toString()
                }
                R.id.moto_camping_radio_btn -> {
                    Log.d(TAG, "AddContentsActivity - 모토캠핑 버튼 클릭")
                    addCampType = moto_camping_radio_btn.text.toString()
                }
                R.id.vanlife_camping_radio_btn -> {
                    Log.d(TAG, "AddContentsActivity - 차박캠핑 버튼 클릭")
                    addCampType = vanlife_camping_radio_btn.text.toString()
                }
                R.id.bivouac_camping_radio_btn -> {
                    Log.d(TAG, "AddContentsActivity - 비박캠핑 버튼 클릭")
                    addCampType = bivouac_camping_radio_btn.text.toString()
                }
            }
            Log.d(TAG, "AddContentsActivity - $addCampType")
        }

        add_camp_complete_btn.setOnClickListener {
            addTitle = add_camp_title_text.text.toString()
            addCampSite = add_campsite_name_text.text.toString()
            addCampBudget = add_camp_budget_text.text.toString().toInt()

            campData = CampData(
                camp_num = 0,
                campTitle = addTitle,
                campStartDate = addCampStartDate,
                campEndDate = addCampEndDate,
                campSiteName = addCampSite,
                campLocation = addCampLocation,
                campType = addCampType,
                campBudget = addCampBudget
            )

            database?.campDataDao()?.insertAll(campData) //Add All Contents in DB
            addDataList.add(campData)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }//setOnClickListener
    }//onCreate

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
