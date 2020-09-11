package com.wiserock.heruri.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    var week: Int,
    var name: String,
    var doen: Boolean
) : Parcelable