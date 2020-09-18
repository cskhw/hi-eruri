package com.wiserock.template.model.user

import androidx.room.*

@Dao
interface UserDAO {

    @Query("Select * From user")
    fun getAll(): List<UserEntity>

    @Insert
    fun insert(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)
}




