package com.baileytye.examprep.ui.pager

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class DefaultPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            alpha = 1f
            translationX = 0f
            translationZ = 0f
            scaleX = 1f
            scaleY = 1f
        }
    }
}