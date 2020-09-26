package com.wiserock.heruri.model.day

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Dao
import androidx.room.Query

@Dao
interface DayDao {
    @Query("select * from day")
    fun getAllPlan(): List<DayWithPlan>
}

