package com.baileytye.examprep.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.baileytye.examprep.R

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideKeyboard() {
    val imm = activity?.hideKeyboard()
}


/**
 * Purpose: Adds vertical spacing above recycler items, or if a grid, add right spacing to even indexed items
 * Parameters: spacing - height between items, grid - true to use GridSpaceItemDecoration (add spacing to sides)
 * Requires: GridSpaceItemDecoration.kt, VerticalSpaceItemDecoration.kt, default spacing declared as R.dimen.default_recycler_spacing in dimen
 * Usage: recyclerView.addSpacing(16) - Adds 16dp spacing for a linear list. Can be used like: recyclerView.addSpacing() if R.dimen.default_recycler_spacing is defined.
 *        To use a grid layout: recyclerView.addSpacing(16, true)
 * Source: Brain
 */
fun RecyclerView.addSpacing(
    spacing: Int = resources.getDimension(R.dimen.default_recycler_spacing).toInt()
) {
    addItemDecoration(
        VerticalSpaceItemDecoration(
            spacing
        )
    )
}

