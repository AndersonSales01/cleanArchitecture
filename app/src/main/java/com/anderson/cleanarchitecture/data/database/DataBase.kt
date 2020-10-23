package com.anderson.cleanarchitecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginPO::class], version = 1,exportSchema = false)
abstract class DataBase () : RoomDatabase()  {
    abstract fun loginDao(): LoginDao
}