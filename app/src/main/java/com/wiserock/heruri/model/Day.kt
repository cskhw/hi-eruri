package com.wiserock.heruri.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "day"
)
data class Day(

    @ColumnInfo(name = "day")
    var day: String
)