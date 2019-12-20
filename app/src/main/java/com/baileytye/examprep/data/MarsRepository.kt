package com.baileytye.examprep.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData

object MarsRepository {
    suspend fun getProperties(): LiveData<Result<List<MarsProperty>>> = liveData {
        emitSource(MutableLiveData(Result.Loading()))
        try {
            emitSource(
                MutableLiveData(
                    Result.Success(
                        MarsApi.retrofitService.getProperties().subList(
                            0,
                            100
                        )
                    )
                )
            )
        } catch (e: Exception) {
            emitSource(MutableLiveData(Result.Error(e.message)))
            println("DEBUG: Error: ${e.localizedMessage}")
        }
    }

}