package com.wiserock.heruri.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(
    var id: Long,
    var name: String,
    var done: String,
    var href: String,
    var professor: String,
    var deadline: String
) : Parcelable