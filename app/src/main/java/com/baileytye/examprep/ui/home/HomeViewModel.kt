package com.baileytye.examprep.ui.home

import androidx.lifecycle.ViewModel

//Cannot be injected since homeNavigator requires activity as implementation
class HomeViewModel(private val homeNavigator: HomeNavigator) : ViewModel() {
    fun startMirrorText() {
        homeNavigator.onStartMirrorText()
    }

    fun startReceiveUser() {
        homeNavigator.onStartReceiveUser()
    }

    fun startUserList() {
        homeNavigator.onStartUserList()
    }
}