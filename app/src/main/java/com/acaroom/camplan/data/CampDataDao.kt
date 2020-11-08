package com.acaroom.camplan.data

import androidx.room.*

@Dao
interface CampDataDao {
    @Query("SELECT * FROM camp_data")
    fun getAll(): ArrayList<CampData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg campData: CampData)

    @Update
    fun update(vararg  campData: CampData)

    @Delete
    fun delete(campData: CampData)
}