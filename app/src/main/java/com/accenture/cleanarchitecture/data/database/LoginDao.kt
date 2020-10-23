package com.accenture.cleanarchitecture.data.database

import androidx.room.*

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: LoginPO): Long

    @Update
    fun update(user: LoginPO): Int

    @Delete
    fun delete(user: LoginPO)

    @Query("SELECT * FROM login WHERE email = :email")
    suspend fun getUserEmail(email: String): LoginPO

    @Query("SELECT * FROM login WHERE email = :email AND password = :password ")
    suspend fun getCreditial(email: String, password: String): LoginPO
}