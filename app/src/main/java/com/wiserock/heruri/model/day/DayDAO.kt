package com.wiserock.heruri.model.day

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DayDAO {

    @Transaction
    @Query("select * from day")
    fun getAllPlan(): List<DayWithPlan>

    @Insert
    fun insert(day: Day)

    @Insert
    fun insertDayWithPlan(dayWithPlan: DayWithPlan)
}

