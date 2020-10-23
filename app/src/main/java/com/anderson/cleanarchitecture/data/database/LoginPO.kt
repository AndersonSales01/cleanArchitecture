package com.anderson.cleanarchitecture.data.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class LoginPO(
    @ColumnInfo(name = "name")
    @NonNull
    var name: String = "",
    @ColumnInfo(name = "email")
    @NonNull
    var email: String = "",
    @ColumnInfo(name = "password")
    @NonNull
    var password: String = "",
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
)