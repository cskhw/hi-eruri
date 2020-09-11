package com.wiserock.template.model

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wiserock.template.model.user.UserDAO

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        const val DB_NAME = "application-db"
        val instance: AppDatabase? = null
    }

    fun getInstance(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context)
        }
    }

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                // write callback function
            })
            .build()
    }
}
