package com.example.library.RoomDataBase

import android.content.ClipData
import android.content.Context
import android.os.AsyncTask

class UserTask(context: Context) : AsyncTask<User, Void, Void>() {
    val userDao = UsersDatabase.getInstance(context).userDao()
    override fun doInBackground(vararg params: User?): Void {
        userDao.insertUser(params[0]!!)
        return Void.TYPE.newInstance()
    }

    class UserTaskItem(item: ClipData.Item, users: List<User>) {
        enum class action {
            INSERT
        }
        val item: ClipData.Item? = null
        val users: List<User>? = null
    }
}