package com.example.library
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val ListView = ListBooks()

        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,ListView).commit()

        //First line to commit


    }

}
