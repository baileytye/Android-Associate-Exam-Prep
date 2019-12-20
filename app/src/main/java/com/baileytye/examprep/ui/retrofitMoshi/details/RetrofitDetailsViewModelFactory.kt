package com.baileytye.examprep.ui.retrofitMoshi.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baileytye.examprep.data.MarsProperty

class RetrofitDetailsViewModelFactory(private val marsProperty: MarsProperty) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RetrofitDetailsViewModel::class.java)) {
            return RetrofitDetailsViewModel(marsProperty) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}