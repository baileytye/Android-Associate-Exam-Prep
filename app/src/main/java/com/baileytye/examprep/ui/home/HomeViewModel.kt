package com.baileytye.examprep.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

//Cannot be injected since homeNavigator requires activity as implementation
class HomeViewModel : ViewModel() {

    private var homeNavigator: WeakReference<HomeNavigator>? = null

    //Instead of being updated by the fragment, this would normally be updated here with
    //shared prefs being injected by dagger, and could override set to auto update shared prefs
    var counter = MutableLiveData(0)

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

    fun startValidation() {
        homeNavigator?.get()?.onStartValidation()
    }

    fun startCanvas() {
        homeNavigator?.get()?.onStartCanvas()
    }

    fun startPager() {
        homeNavigator?.get()?.onStartPager()
    }
}