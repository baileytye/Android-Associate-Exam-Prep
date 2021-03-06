package com.baileytye.examprep.ui.retrofitMoshi

import androidx.lifecycle.*
import com.baileytye.examprep.data.Event
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

    private val _navigateToSelectedProperty = MutableLiveData<Event<MarsProperty>>()
    val navigateToSelectedProperty: LiveData<Event<MarsProperty>>
        get() = _navigateToSelectedProperty

    val contentVisibility = _properties.map {
        it is Result.Success
    }

    val loadingVisibility = _properties.map {
        it is Result.Loading
    }

    val errorVisibility = _properties.map {
        it is Result.Error
    }

    val errorText = _properties.map {
        when (it) {
            is Result.Error -> "Error: ${it.message}"
            else -> ""
        }
    }

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = Event(marsProperty)
    }

    private fun getMarsProperties() {
        _properties = liveData {
            emitSource(marsRepository.getProperties())
        }
    }

}
