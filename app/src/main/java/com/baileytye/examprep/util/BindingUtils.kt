package com.baileytye.examprep.util

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.baileytye.examprep.data.User
import kotlin.random.Random

//This does nothing better than @{user.firstname} on text xml field, but if formatting is required
//this is where it would happen
@BindingAdapter("firstNameFormatted")
fun TextView.setUserFirstNameFormatted(item: User) {
    item.let {
        text = item.firstName
    }
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