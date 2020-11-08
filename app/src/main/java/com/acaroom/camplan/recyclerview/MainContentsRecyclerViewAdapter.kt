package com.acaroom.camplan.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acaroom.camplan.R
import com.acaroom.camplan.data.CampData

class MainContentsRecyclerViewAdapter(val context: Context, private val contentsList: ArrayList<CampData>)
                                        : RecyclerView.Adapter<MainContentItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainContentItemViewHolder {

        return MainContentItemViewHolder(LayoutInflater.from(parent.context)
                                            .inflate(R.layout.main_content_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainContentItemViewHolder, position: Int) {
        holder.bindWithView(this.contentsList[position])
    }

    override fun getItemCount(): Int {
        return contentsList.size
    }

    //외부에서 어답터에 데이터 배열을 넣어준다.
//    fun submitList(contentsList: ArrayList<CampData>) {
//        this.contentsList = contentsList
//    }

}