package com.wiserock.template.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wiserock.heruri.model.day.DayDAO
import com.wiserock.heruri.model.push.PushDAO
import com.wiserock.heruri.model.push.PushEntity
import com.wiserock.template.model.user.UserDAO
import com.wiserock.template.model.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PushEntity::class
    ], version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun pushDAO(): PushDAO
    abstract fun dayDAO(): DayDAO

    companion object {
        const val DB_NAME = "application-db"
        val instance: AppDatabase? = null


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
}
