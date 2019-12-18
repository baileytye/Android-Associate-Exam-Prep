package com.baileytye.examprep.ui.retrofitMoshi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baileytye.examprep.data.MarsProperty
import com.baileytye.examprep.data.MarsRepository
import com.baileytye.examprep.data.Result
import kotlinx.coroutines.launch

class RetrofitMoshiViewModel : ViewModel() {

    private val marsRepository = MarsRepository
    var properties: LiveData<Result<List<MarsProperty>>>

    init {
        properties = MutableLiveData(Result.Loading())
        getMarsProperties()
    }

    private fun getMarsProperties() {
        viewModelScope.launch {
            properties = marsRepository.getProperties()
        }
    }
}
