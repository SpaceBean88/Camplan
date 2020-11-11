package com.acaroom.camplan.activities

import android.annotation.SuppressLint
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
import com.acaroom.camplan.data.CampData
import com.acaroom.camplan.utils.Constants.TAG
import kotlinx.android.synthetic.main.activity_add_contents.*

class AddContentsActivity : AppCompatActivity() {

    //Calendar
    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contents)

        start_date_btn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                start_date_text.text =
                    year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
            }, year, month, day)
            datePickerDialog.show()
        }

        end_date_btn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                end_date_text.text =
                    year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
            }, year, month, day)
            datePickerDialog.show()
        }

        //Camping Area Select
        val areaItems = resources.getStringArray(R.array.area_array)
        var selectedLocation:String = ""
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, areaItems)
        area_list_spinner.adapter = adapter
        area_list_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedLocation = areaItems[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //Camping Div RadioBtn
        var campTypeRBtn: String = ""
        camp_div_radio_group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.auto_camping_radio_btn -> {
                    campTypeRBtn = auto_camping_radio_btn.text.toString()
                }
                R.id.moto_camping_radio_btn -> {
                    campTypeRBtn = moto_camping_radio_btn.text.toString()
                }
                R.id.vanlife_camping_radio_btn -> {
                    campTypeRBtn = vanlife_camping_radio_btn.text.toString()
                }
                R.id.bivouac_camping_radio_btn -> {
                    campTypeRBtn = bivouac_camping_radio_btn.text.toString()
                }
            }
            Log.d(TAG, "AddContentsActivity - 선택된 버튼: $campTypeRBtn")
        }

        add_camp_complete_btn.setOnClickListener {
            val campData = CampData(
                0,
                add_camp_title_text.text.toString(),
                start_date_text.text.toString(),
                end_date_text.text.toString(),
                add_campsite_name_text.text.toString(),
                selectedLocation,
                campTypeRBtn,
                add_camp_budget_text.text.toString().toInt()
            )

            val intent = Intent()
            intent.putExtra("camp_data", campData)
            setResult(RESULT_OK, intent)
            finish()
        }//setOnClickListener
    }//onCreate

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
