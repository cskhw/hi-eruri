package com.wiserock.heruri.model.day

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "day"
)
data class Day(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var time: Long? = 0,
    var day: String
)