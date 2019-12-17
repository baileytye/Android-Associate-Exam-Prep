package com.baileytye.examprep.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Class used to add space to top of each recycler item
 * @param space : The space to add between each item
 */
class VerticalSpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space
    }
}


/**
 * Class used to add space to right and top of each item for a recycler view layed out as a grid
 * @param space: The space to add between each item
 */
class GridSpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space
        if (parent.getChildLayoutPosition(view) % 2 == 0) outRect.right = space
    }
}