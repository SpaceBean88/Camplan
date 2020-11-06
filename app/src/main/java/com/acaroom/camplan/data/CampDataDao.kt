package com.acaroom.camplan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CampDataDao {
    @Query("SELECT * FROM camp_data")
    fun getAll(): List<CampData>

    @Insert
    fun insertAll(vararg campData: CampData)

    @Delete
    fun delete(campData: CampData)
}