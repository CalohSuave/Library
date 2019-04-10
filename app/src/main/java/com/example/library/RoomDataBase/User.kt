package com.example.library.RoomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "users")
data class User(@PrimaryKey
                @ColumnInfo(name = "userid")
                val id: String,
                @ColumnInfo(name = "email")
                val email: String,
                @ColumnInfo(name = "username")
                val userName: String,
                @ColumnInfo(name = "password")
                val password: String,
                @ColumnInfo(name = "favouritebook")
                val favouriteBook: ArrayList<Book>?
)