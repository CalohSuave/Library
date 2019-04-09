package com.example.library
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.example.library.ListBooksFragment.OnButtonPressedListener
import com.example.library.LoginFragment.onLogindPressed
import com.example.library.RegisterFragment.OnRegisterFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnButtonPressedListener, onLogindPressed, OnRegisterFragment{


    override fun onRegisterButtonPressed(email: String, name: String, Password: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoginFragmentRegister() {

        val registerFragment = RegisterFragment()
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer, registerFragment).addToBackStack(null)
            .commit()

    }


    override fun onRegisterFragment(text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onLoginFragmentInteraction(text: String) {

        val listBooks = ListBooksFragment()
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer, listBooks).addToBackStack(null).commit()



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
