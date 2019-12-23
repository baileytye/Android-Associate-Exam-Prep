package com.baileytye.examprep.util

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.baileytye.examprep.R
import com.baileytye.examprep.data.MarsProperty
import com.baileytye.examprep.data.Result
import com.baileytye.examprep.data.User
import com.baileytye.examprep.ui.retrofitMoshi.RetrofitAdapter
import com.baileytye.examprep.ui.userList.UserListAdapter
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

//This does nothing better than @{user.firstname} on text xml field, but if formatting is required
//this is where it would happen
@BindingAdapter("firstNameFormatted")
fun TextView.setUserFirstNameFormatted(item: User) {
    item.let {
        text = item.firstName
    }
}

@BindingAdapter("android:text")
fun TextView.setText(double: Double) {
    text = double.toString()
}

//User isn't used, but normally you would change the image based on the input
@BindingAdapter("randomColor")
fun ImageView.setRandomColor(user: User) {
    val colors = listOf(
        Color.BLUE,
        Color.CYAN,
        Color.GREEN,
        Color.RED,
        Color.YELLOW
    )

    val index = Random.nextInt(0, colors.size)
    val color = colors[index]
    background = ShapeDrawable(OvalShape()).apply { paint.color = color }
}

@BindingAdapter("android:visibility")
fun View.setVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("marsImage")
fun ImageView.setMarsImage(url: String) {
    load(url)
}

@BindingAdapter("userList")
fun RecyclerView.bindUserRecyclerView(data: List<User>) {
    val adapter = adapter as UserListAdapter
    adapter.submitList(data.toList())
}

@BindingAdapter("listData")
fun RecyclerView.bindRecyclerView(data: Result<List<MarsProperty>>) {
    val adapter = this.adapter as RetrofitAdapter
    when (data) {
        is Result.Success -> adapter.submitList(data.data)
        else -> {
        }
    }
}

@BindingAdapter("errorText")
fun TextInputLayout.setErrorMessage(errorMessage: String) {
    error = errorMessage
}

@BindingAdapter("loadingButton")
fun Button.setLoading(loading: Boolean) {
    if (loading) {
        text = "Validating"
        this.setCompoundDrawables(null, null, CircularProgressDrawable(context).apply {
            setStyle(CircularProgressDrawable.LARGE)
            setColorSchemeColors(Color.WHITE)
            val size = (centerRadius + strokeWidth).toInt() * 2
            setBounds(0, 0, size, size)
            start()
        }, null)
    } else {
        text = context.getString(R.string.button_validate)
        this.setCompoundDrawables(null, null, null, null)
    }
}


