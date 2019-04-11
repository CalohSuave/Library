package com.example.library.RoomDataBase

import android.content.ClipData
import android.content.Context
import android.os.AsyncTask

class UserBookTask(context: Context) : AsyncTask<UserBook, Void, Void>() {
    val userBookDao = UsersDatabase.getInstance(context).userBookDao()
    override fun doInBackground(vararg params: UserBook?): Void {
        userBookDao.insertFavoriteBook(params[0]!!)
        return Void.TYPE.newInstance()
    }

    class UserBookTaskItem(item: ClipData.Item, book: Book) {
        enum class action {
            INSERT, DELETE
        }
        val item: ClipData.Item? = null
        val books: List<Book>? = null
    }
}