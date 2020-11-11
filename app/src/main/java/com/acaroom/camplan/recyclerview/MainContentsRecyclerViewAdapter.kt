package com.acaroom.camplan.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acaroom.camplan.R
import com.acaroom.camplan.data.CampData

class MainContentsRecyclerViewAdapter internal constructor (val context: Context)
                                        : RecyclerView.Adapter<MainContentItemViewHolder>() {

    private var contentsList: List<CampData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainContentItemViewHolder {

        return MainContentItemViewHolder(LayoutInflater.from(parent.context)
                                            .inflate(R.layout.main_content_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainContentItemViewHolder, position: Int) {
        if(contentsList != null)
            return holder.bindWithView(this.contentsList!![position])
    }

    override fun getItemCount(): Int {
        return if (contentsList != null) contentsList!!.size else 0
    }

    //외부에서 어답터에 데이터 배열을 넣어준다.
    internal fun setList(contentsList: List<CampData>) {
        this.contentsList = contentsList
        notifyDataSetChanged()
    }

}