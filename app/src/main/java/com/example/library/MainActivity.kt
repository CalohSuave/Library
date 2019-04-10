package com.example.library
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.example.library.ListBooksFragment.OnListBookCellPressed
import com.example.library.LoginFragment.OnLoginFragmentPressed
import com.example.library.RegisterFragment.OnRegisterFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnListBookCellPressed, OnLoginFragmentPressed, OnRegisterFragment{



    override fun isUserOnDataBase() {

        val listBooks = ListBooksFragment()
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer, listBooks).addToBackStack(null).commit()

    }

    override fun goToRegisterFragment(email: String, password: String) {


        val registerFragment = RegisterFragment.newInstance(email, password)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer, registerFragment).addToBackStack(null)
            .commit()
    }


    override fun onRegisterButtonPressed(email: String, name: String, Password: String) {

    }

    override fun onRegisterFragment(text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onButton(books: Book) {

        val detailFragment = ShowDetailBookFragment.newInstance(books)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,detailFragment).addToBackStack(null).commit()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)
        val LoginFrag = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,LoginFrag).commit()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
