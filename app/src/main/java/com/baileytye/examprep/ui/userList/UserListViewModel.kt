package com.baileytye.examprep.ui.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baileytye.examprep.data.User

class UserListViewModel : ViewModel() {

    private val _userList = MutableLiveData<MutableList<User>>()

    val userList: LiveData<MutableList<User>>
        get() = _userList

    init {
        var id = 0
        _userList.value = mutableListOf(
            User("Garry", "Smith", id++),
            User("John", "Doe", id++),
            User("John", "Doe", id++),
            User("John", "Doe", id++),
            User("John", "Doe", id++),
            User("John", "Doe", id)
        )
    }

    fun removeUser(user: User) {
        _userList.value?.let {
            _userList.value = _userList.value?.apply { remove(user) }
        }
    }
}
