package com.example.library
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity(), ListBooks.OnButtonPressedListener{

    override fun onButton(books: Libro) {

        val detailFragment = ShowDetailBook.newInstance(books)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,detailFragment).commit()


    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

<<<<<<< HEAD

        val ListView = LoginFragment()

=======
        val ListView = ListBooks()
>>>>>>> 82af9f2ebba3dfefd4e1a6c73f9ad81d8f05b443
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,ListView).commit()




    }


}
