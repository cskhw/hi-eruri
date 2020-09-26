package com.wiserock.heruri.model.day

import androidx.room.Embedded
import androidx.room.Relation
import com.wiserock.heruri.model.Homework

class DayWithPlan {
    @Embedded
    lateinit var day: Day

    @Relation(
        parentColumn = "time",
        entityColumn = "time",
        entity = Homework::class
    )
    lateinit var homeworkList: List<Homework>
}