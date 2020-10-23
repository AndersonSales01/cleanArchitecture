package com.accenture.cleanarchitecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject

@Database(entities = [LoginPO::class], version = 1,exportSchema = false)
abstract class DataBase () : RoomDatabase()  {
    abstract fun loginDao(): LoginDao
}