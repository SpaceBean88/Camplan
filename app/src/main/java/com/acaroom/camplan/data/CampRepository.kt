package com.acaroom.camplan.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class CampRepository(app: Application) {
    companion object {
        private class InsertAsyncTask constructor(private val asyncTaskDao: CampDataDao)
            : AsyncTask<CampData, Void, Void>() {
            override fun doInBackground(vararg params: CampData?): Void? {
                asyncTaskDao.insert(params)
                return null
            }
        }
    }

    private lateinit var campDataDao: CampDataDao
    private val allData: LiveData<List<CampData>>

    init {
        val db = AppDatabase.getInstance(app)
        if (db != null) {
            campDataDao = db.campDataDao()
        }
        allData = campDataDao.getAll()
    }

    fun getAll(): LiveData<List<CampData>> {
        return allData
    }

    fun insert(campData: CampData) {
        InsertAsyncTask(campDataDao).execute(campData)
    }

}