package com.acaroom.camplan.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acaroom.camplan.R
import com.acaroom.camplan.data.AddCampingData

class MainContentsRecyclerViewAdapter : RecyclerView.Adapter<MainContentItemViewHolder>() {

    private var contentsList = ArrayList<AddCampingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainContentItemViewHolder {
        val mainContentItemViewHolder = MainContentItemViewHolder(LayoutInflater.from(parent.context)
                                            .inflate(R.layout.main_content_item, parent, false))

        return mainContentItemViewHolder
    }

    override fun onBindViewHolder(holder: MainContentItemViewHolder, position: Int) {
        holder.bindWithView(this.contentsList[position])
    }

    override fun getItemCount(): Int {
        return contentsList.size
    }

}