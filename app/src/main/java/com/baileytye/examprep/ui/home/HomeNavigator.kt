package com.baileytye.examprep.ui.home

//I don't like this because it cannot be injected into viewModel with dagger
interface HomeNavigator {
    fun onStartMirrorText()
    fun onStartReceiveUser()
    fun onStartUserList()
}