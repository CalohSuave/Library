package com.example.library.RoomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(@PrimaryKey
                @ColumnInfo(name = "userid")
                val userid: String,
                @ColumnInfo(name = "email")
                val email: String,
                @ColumnInfo(name = "username")
                val userName: String,
                @ColumnInfo(name = "password")
                val password: String
)