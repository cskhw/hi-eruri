package com.wiserock.heruri.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(
    var id: Int,
    var name: String,
    var professor: String,
    var homeworks: ArrayList<Homework>,
    var videos: ArrayList<Video>
) : Parcelable