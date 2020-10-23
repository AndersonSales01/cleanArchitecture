package com.anderson.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.anderson.cleanarchitecture.data.database.DataBase
import com.anderson.cleanarchitecture.data.database.LoginDao
import com.anderson.cleanarchitecture.util.SharedPreferenceUtil
import dagger.Module

import dagger.Provides

import javax.inject.Singleton

@Module
class DataBaseModule {


    @Singleton
    @Provides
    fun provideDataBase(context: Context ): DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, "db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginDao(myDatabase: DataBase): LoginDao {
        return myDatabase.loginDao()
    }

    @Singleton
    @Provides
    fun providerSharesPreferenceUtil(context: Context) : SharedPreferenceUtil {
        return SharedPreferenceUtil(context)
    }

}