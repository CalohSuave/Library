package com.example.library
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity(), ListBooksFragment.OnButtonPressedListener{

    override fun onButton(books: Book) {

        val detailFragment = ShowDetailBookFragment.newInstance(books)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,detailFragment).addToBackStack(null).commit()


    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ListView = ListBooksFragment()
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,ListView).commit()




    }


}
