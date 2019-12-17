package com.baileytye.examprep.ui.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baileytye.examprep.data.User

class UserListViewModel : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()

    val userList: LiveData<List<User>>
        get() = _userList

    init {
        _userList.value = listOf(User("Garry", "Smith"), User("John", "Doe"))
    }
}
