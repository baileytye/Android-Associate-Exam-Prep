package com.baileytye.examprep.ui.retrofitMoshi

import androidx.lifecycle.*
import com.baileytye.examprep.data.MarsProperty
import com.baileytye.examprep.data.MarsRepository
import com.baileytye.examprep.data.Result

class RetrofitMoshiViewModel : ViewModel() {

    private val marsRepository = MarsRepository
    private var _properties: LiveData<Result<List<MarsProperty>>> =
        MutableLiveData(Result.Loading())
    val properties: LiveData<Result<List<MarsProperty>>>
        get() = _properties

    init {
        getMarsProperties()
    }

    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    val navigateToSelectedProperty: LiveData<MarsProperty>
        get() = _navigateToSelectedProperty

    val contentVisibility = Transformations.map(_properties) {
        it is Result.Success
    }

    val loadingVisibility = Transformations.map(_properties) {
        it is Result.Loading
    }

    val errorVisibility = Transformations.map(_properties) {
        it is Result.Error
    }

    val errorText = Transformations.map(_properties) {
        when (it) {
            is Result.Error -> "Error: ${it.message}"
            else -> ""
        }
    }

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    private fun getMarsProperties() {
        _properties = liveData {
            emitSource(marsRepository.getProperties())
        }
    }

}
