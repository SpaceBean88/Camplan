package com.acaroom.camplan.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acaroom.camplan.utils.Constants.TAG

enum class Calculate{
    EXPENSES, BUDGET
}

class ExpensesViewModel : ViewModel() {
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    init {
        Log.d(TAG, "ExpensesViewModel - () called")
        _currentValue.value = 0
    }

    fun updateValue(calculate: Calculate, input: Int) {
        when(calculate) {
            Calculate.BUDGET ->
                _currentValue.value = _currentValue.value?.plus(input)
            Calculate.EXPENSES ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}

