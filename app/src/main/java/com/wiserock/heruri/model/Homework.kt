package com.wiserock.heruri.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Homework(
    var id: Int,
    var course: String,
    var name: String,
    var done: Boolean,
    var deadline: String,
    var href: String
) : Parcelable