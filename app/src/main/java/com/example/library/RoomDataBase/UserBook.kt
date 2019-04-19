package com.example.library.RoomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userbook", primaryKeys = ["bookid","userid"])
data class UserBook(
                @ColumnInfo(name = "bookid")
                val bookid: String,
                @ColumnInfo(name = "userid")
                val userid: String)