package com.acaroom.camplan.recyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.acaroom.camplan.data.CampData
import com.acaroom.camplan.utils.Constants.TAG
import kotlinx.android.synthetic.main.main_content_item.view.*

class MainContentItemViewHolder(item : View) : RecyclerView.ViewHolder(item) {

    private val campTitleText = item.main_camp_title_text
    private val campSiteText = item.main_camp_site_text
    private val campTypeText = item.main_camp_type_text
    private val campScheduleText = item.main_camp_schedule_text

    fun bindWithView(contentsItem: CampData) {
        Log.d(TAG, "MainContentItemViewHolder - bindWithView() called")

        campTitleText.text = contentsItem.campTitle
        campTypeText.text = contentsItem.campType
        campSiteText.text = contentsItem.campSiteName
        campScheduleText.text = "${contentsItem.campStartDate} ~ ${contentsItem.campEndDate}"
    }

}