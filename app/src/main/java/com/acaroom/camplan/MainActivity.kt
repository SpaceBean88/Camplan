package com.acaroom.camplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.main_toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_nav.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.START) //START = left, END = right
        }

        naviView.setNavigationItemSelectedListener(this) //네비게이션 메뉴 아이템에 클릭 속성 부여
    }

    //네비게이션 메뉴 아이템 클릭 시 수행
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId) {
           R.id.menu_account -> Toast.makeText(applicationContext, "접근성", Toast.LENGTH_SHORT).show()
           R.id.menu_check -> Toast.makeText(applicationContext, "이메일", Toast.LENGTH_SHORT).show()
           R.id.menu_site -> Toast.makeText(applicationContext, "캠프", Toast.LENGTH_SHORT).show()
       }
        layout_drawer.closeDrawers() //네비게이션 뷰 닫기
        return false
    }

    override fun onBackPressed() {
        if(layout_drawer.isDrawerOpen(GravityCompat.START)) {
            layout_drawer.closeDrawers()
        } else {
            super.onBackPressed() //일반 백버튼 기능 실행
        }

    }
}