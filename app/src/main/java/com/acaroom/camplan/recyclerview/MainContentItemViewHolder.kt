package com.acaroom.camplan.recyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.acaroom.camplan.data.AddCampingData
import com.acaroom.camplan.utils.Constants.TAG
import kotlinx.android.synthetic.main.main_content_item.view.*

class MainContentItemViewHolder(item : View) : RecyclerView.ViewHolder(item) {

    private val campTitleText = item.main_camp_title_text
    private val campSiteText = item.main_camp_site_text
    private val campDevText = item.main_camp_dev_text
    private val campScheduleText = item.main_camp_schedule_text

    fun bindWithView(contentsItem: AddCampingData) {
        Log.d(TAG, "MainContentItemViewHolder - bindWithView() called")

        campTitleText.text = contentsItem.campTitle
        campDevText.text = contentsItem.campDevision
        campSiteText.text = contentsItem.campSiteName
        campScheduleText.text = "${contentsItem.campStartDate.toString()} ~ ${contentsItem.campEndDate.toString()}"
    }

}