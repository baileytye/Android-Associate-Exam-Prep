package com.baileytye.examprep.ui.home

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

//Cannot be injected since homeNavigator requires activity as implementation
class HomeViewModel : ViewModel() {

    private var homeNavigator: WeakReference<HomeNavigator>? = null

    fun setNavigator(navigator: HomeNavigator) {
        homeNavigator = WeakReference(navigator)
    }

    fun startMirrorText() {
        homeNavigator?.get()?.onStartMirrorText()
    }

    fun startReceiveUser() {
        homeNavigator?.get()?.onStartReceiveUser()
    }

    fun startUserList() {
        homeNavigator?.get()?.onStartUserList()
    }

    fun startRetrofit() {
        homeNavigator?.get()?.onStartRetrofit()
    }
}