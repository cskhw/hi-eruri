package com.wiserock.heruri.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(
    var id: Int?,
    var name: String,
    var href: String,
    var professor: String,
    var homework: ArrayList<Homework>,
    var videos: ArrayList<Video>
) : Parcelable