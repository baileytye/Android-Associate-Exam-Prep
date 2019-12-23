package com.baileytye.examprep.util

import android.app.Activity
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.baileytye.examprep.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.textfield.TextInputLayout

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
    activity?.hideKeyboard()
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

/**
 * Purpose: Retrieves a value from shared prefs
 * Parameters:  key [Required] - string key of value stored in shared prefs
 *              defaultValue [Required] - default value to return if not present
 * Requires: -
 * Usage:  val x = sharedPreferences.get(KEY, defaultValueX)
 * Source: Stackoverflow
 * Notes: -
 */
inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when (T::class) {
        Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
        Float::class -> return this.getFloat(key, defaultValue as Float) as T
        Int::class -> return this.getInt(key, defaultValue as Int) as T
        Long::class -> return this.getLong(key, defaultValue as Long) as T
        String::class -> return this.getString(key, defaultValue as String) as T
        else -> {
            if (defaultValue is Set<*>) {
                return this.getStringSet(key, defaultValue as Set<String>) as T
            }
        }
    }
    return defaultValue
}

/**
 * Purpose: Writes a value to shared prefs
 * Parameters:  key [Required] - string key of value to store in shared prefs
 *              value [Required] - value to store
 * Requires: -
 * Usage:  sharedPreferences.put(KEY, myValue)
 * Source: Stackoverflow
 * Notes: -
 */
inline fun <reified T> SharedPreferences.put(key: String, value: T) {
    val editor = this.edit()

    when (T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
        else -> {
            if (value is Set<*>) {
                editor.putStringSet(key, value as Set<String>)
            }
        }
    }
    editor.commit()
}

fun ImageView.load(
    url: String,
    loadOnlyFromCache: Boolean = false,
    onLoadingFinished: () -> Unit = {}
) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished()
            return false
        }

    }
    val requestOptions = RequestOptions.placeholderOf(R.drawable.loading_animation)
        .dontTransform()
        .error(R.drawable.ic_broken_image_24dp)
        .onlyRetrieveFromCache(loadOnlyFromCache)
    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .listener(listener)
        .into(this)
}

fun TextInputLayout.clearError() {
    error = null
}


