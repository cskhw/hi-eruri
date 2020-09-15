package com.wiserock.heruri.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Homework(
    var course: String,
    var name: String,
    var done: Boolean,
    var deadline: String
) : Parcelable