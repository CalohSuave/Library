package com.example.library.RoomDataBase

import android.content.ClipData
import android.content.Context
import android.os.AsyncTask

class BookTask(context: Context) : AsyncTask<Book, Void, Void>() {
    val bookDao = UsersDatabase.getInstance(context).bookDao()
    override fun doInBackground(vararg params: Book): Void {
        bookDao.insertBook(params[0])
        return Void.TYPE.newInstance()
    }
}