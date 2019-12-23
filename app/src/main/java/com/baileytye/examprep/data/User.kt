package com.baileytye.examprep.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val firstName: String, val lastName: String, val id: Int = 0) : Parcelable