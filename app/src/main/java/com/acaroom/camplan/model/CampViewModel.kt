package com.acaroom.camplan.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acaroom.camplan.data.CampData
import com.acaroom.camplan.data.CampRepository

class CampViewModel(app: Application) : AndroidViewModel(app) {
    private val repository : CampRepository = CampRepository(app)
    private val allCamps: LiveData<List<CampData>> = repository.getAll()

    fun getAll(): LiveData<List<CampData>> = allCamps
    fun insert(campData: CampData) {
        repository.insert(campData)
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CampViewModel(application) as T
        }

    }
}