package com.baileytye.examprep.ui.pager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.baileytye.examprep.data.Event

class PagerViewModel : ViewModel() {
    private var _pages: MutableLiveData<List<String>> =
        MutableLiveData(listOf("First page", "2", "Three"))
    val pages: LiveData<List<String>>
        get() = _pages

    private var _transformer: ViewPager2.PageTransformer = DefaultPageTransformer()
    val transformer: ViewPager2.PageTransformer
        get() = _transformer

    private var _showSnackbarEvent = MutableLiveData<Event<Boolean>>()
    val showSnackBarEvent: LiveData<Event<Boolean>>
        get() = _showSnackbarEvent

    fun toggleTransformer(): ViewPager2.PageTransformer {
        _transformer = when (transformer) {
            is DepthPageTransformer -> DefaultPageTransformer()
            is ZoomOutPageTransformer -> DepthPageTransformer()
            else -> ZoomOutPageTransformer()
        }
        _showSnackbarEvent.value = Event(true)
        return transformer
    }
}
