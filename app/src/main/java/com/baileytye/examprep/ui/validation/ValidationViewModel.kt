package com.baileytye.examprep.ui.validation

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baileytye.examprep.data.Event
import com.baileytye.examprep.util.SimpleIdlingResource
import kotlinx.coroutines.launch


class ValidationViewModel : ViewModel() {

    @Nullable
    private var simpleIdlingResource: SimpleIdlingResource? = null

    val email = MutableLiveData("")

    val password = MutableLiveData("")

    val emailErrorText = MutableLiveData("")

    val emailErrorPresent = MutableLiveData(false)

    val validating = MutableLiveData(false)

    val passwordErrorText = MutableLiveData("")

    val passwordErrorPresent = MutableLiveData(false)

    private var _showSnackbarEvent = MutableLiveData<Event<Boolean>>()
    val showSnackBarEvent: LiveData<Event<Boolean>>
        get() = _showSnackbarEvent

    private fun validateEmail(): Boolean {
        email.value?.let {
            return if (com.baileytye.examprep.util.validateEmail(it)) {
                emailErrorPresent.value = false
                emailErrorText.value = ""
                true
            } else {
                emailErrorPresent.value = true
                emailErrorText.value = "Invalid email"
                false
            }
        }
        return false
    }

    private fun validatePassword(): Boolean {
        password.value?.let {
            return if (com.baileytye.examprep.util.validatePassword(it)) {
                passwordErrorPresent.value = false
                passwordErrorText.value = ""
                true
            } else {
                passwordErrorPresent.value = true
                passwordErrorText.value = "Invalid password"
                false
            }
        }
        return false
    }

    fun clearEmailError() {
        emailErrorPresent.value = false
    }

    fun clearPasswordError() {
        passwordErrorPresent.value = false
    }

    fun validate() {
        simpleIdlingResource?.setIdleState(false)
        viewModelScope.launch {
            validating.value = true
            if (validateEmail() && validatePassword()) _showSnackbarEvent.value = Event(true)
            validating.value = false
            simpleIdlingResource?.setIdleState(true)
        }
    }

    @VisibleForTesting
    @NonNull
    fun getIdlingResource(): SimpleIdlingResource {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = SimpleIdlingResource()
        }
        return simpleIdlingResource as SimpleIdlingResource
    }
}

