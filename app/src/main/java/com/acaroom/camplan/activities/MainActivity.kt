package com.acaroom.camplan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acaroom.camplan.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_write_btn.setOnClickListener {
            val intent = Intent(this, AddContentsActivity::class.java)
            startActivity(intent)
        }
    }
}