package com.baileytye.examprep.util

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baileytye.examprep.R
import com.baileytye.examprep.data.MarsProperty
import com.baileytye.examprep.data.Result
import com.baileytye.examprep.data.User
import com.baileytye.examprep.ui.retrofitMoshi.RetrofitAdapter
import com.bumptech.glide.Glide
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
    val uri = url.toUri().buildUpon().scheme("https").build()
    Glide.with(context).load(uri)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image_24dp)
        .centerCrop()
        .into(this)

}

@BindingAdapter("listData")
fun RecyclerView.beingRecyclerView(data: Result<List<MarsProperty>>) {
    val adapter = this.adapter as RetrofitAdapter
    when (data) {
        is Result.Success -> adapter.submitList(data.data)
        else -> {
        }
    }
}