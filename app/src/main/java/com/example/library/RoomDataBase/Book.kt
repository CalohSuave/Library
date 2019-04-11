package com.example.library.RoomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "book")
data class Book(@PrimaryKey
                @ColumnInfo(name = "bookid")
                val id: String = UUID.randomUUID().toString(),
                @ColumnInfo(name = "title")
                val title: String,
                @ColumnInfo(name = "author")
                val author: String,
                @ColumnInfo(name = "description")
                val description: String,
                @ColumnInfo(name = "imagename") //In this String we insert the name of the image that we going to use as portrait, example: "harry_potter" + .png -> the format of the image will be automatically insert in the code, all image will be png.
                val imageName: String)