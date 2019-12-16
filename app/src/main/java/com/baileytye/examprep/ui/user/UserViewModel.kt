package com.baileytye.examprep.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.baileytye.examprep.data.User

class UserViewModel(userIn: User) : ViewModel() {

    private val _originalUser = MutableLiveData<User>()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    val undoButtonVisible = Transformations.map(_user) {
        _originalUser.value != it
    }

    init {
        _user.value = userIn
        _originalUser.value = userIn
    }

    fun changeUser() {
        _user.value = User("Changed 1", "Changed 2")
    }

    fun undoChangeUser() {
        _user.value = _originalUser.value
        _showSnackbarEvent.value = true
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
}