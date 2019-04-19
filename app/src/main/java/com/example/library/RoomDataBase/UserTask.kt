package com.example.library.RoomDataBase

import android.content.Context
import android.os.AsyncTask
import com.example.library.RegisterFragment

class UserTask() : AsyncTask<Void, Void, Void>() {
    lateinit var userDao: UserDao
    lateinit var registerFragment: RegisterFragment
    lateinit var context: Context
    lateinit var user: User
    override fun doInBackground(vararg params: Void?): Void? {
        userDao.insertUser(user)
        return null
    }
    constructor(registerFragment: RegisterFragment, context: Context, user: User) : this() {
        this.registerFragment = registerFragment
        this.context = context
        this.userDao = UsersDatabase.getInstance(context).userDao()
        this.user = user

    }
    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        registerFragment.redirectLogin(user)
    }
}