package com.wiserock.heruri.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Activity(
    var week: Int,
    var name: String,
    var done: Boolean
) : Parcelable