package com.baileytye.examprep.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baileytye.examprep.data.User

class UserViewModelFactory(private val userIn: User) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userIn) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}