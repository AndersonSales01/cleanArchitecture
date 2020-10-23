package com.accenture.cleanarchitecture.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.accenture.cleanarchitecture.data.database.DataBase
import com.accenture.cleanarchitecture.data.database.LoginDao
import com.accenture.cleanarchitecture.data.repository.RepoPullRequestImpl
import com.accenture.cleanarchitecture.domain.repo.IRepoPullRequest
import com.accenture.cleanarchitecture.domain.usecases.VerifyCredentialExists
import com.accenture.cleanarchitecture.util.SharedPreferenceUtil
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