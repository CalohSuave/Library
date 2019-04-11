package com.example.library.RoomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userbook")
data class UserBook(@PrimaryKey
                @ColumnInfo(name = "bookid")
                val bookid: String,
                @PrimaryKey
                @ColumnInfo(name = "userid")
                val userid: String)