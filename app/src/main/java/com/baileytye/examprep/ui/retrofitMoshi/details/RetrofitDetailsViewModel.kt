package com.baileytye.examprep.ui.retrofitMoshi.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baileytye.examprep.data.MarsProperty

class RetrofitDetailsViewModel(marsProperty: MarsProperty) : ViewModel() {

    private val _selectedProperty = MutableLiveData<MarsProperty>(marsProperty)
    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty
}
