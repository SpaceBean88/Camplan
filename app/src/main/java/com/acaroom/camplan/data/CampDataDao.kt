package com.acaroom.camplan.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CampDataDao {
    @Query("SELECT * FROM camp_data order by camp_num DESC")
    fun getAll(): LiveData<List<CampData>>

    @Query("SELECT * FROM camp_data where camp_num")
    fun getContent(): LiveData<List<CampData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(campData: Array<out CampData?>)

    @Update
    fun update(vararg campData: CampData)

    @Delete
    fun delete(campData: CampData)
}