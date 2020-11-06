package com.acaroom.camplan.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "camp_data")
data class CampData(
    @PrimaryKey(autoGenerate = true)
    val camp_num: Long,
    @ColumnInfo(name = "camp_title")
    var campTitle: String,
    @ColumnInfo(name = "camp_start_date")
    var campStartDate: String,
    @ColumnInfo(name = "camp_end_date")
    var campEndDate: String,
    @ColumnInfo(name = "camp_site_name")
    var campSiteName: String,
    @ColumnInfo(name = "camp_location")
    var campLocation: String,
    @ColumnInfo(name = "camp_type")
    var campType: String,
    @ColumnInfo(name = "camp_budget")
    var campBudget: Int
)