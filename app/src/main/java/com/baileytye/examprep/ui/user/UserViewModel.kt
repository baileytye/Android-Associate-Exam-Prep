package com.baileytye.examprep.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baileytye.examprep.data.User

class UserViewModel(userIn: User) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        _user.value = userIn
    }

    fun changeUser() {
        _user.value = User("Changed 1", "Changed 2")
    }
}