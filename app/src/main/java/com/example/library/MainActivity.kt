package com.example.library
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.library.ListBooksFragment.OnListBookCellPressed
import com.example.library.LoginFragment.OnLoginFragmentPressed
import com.example.library.RegisterFragment.OnRegisterFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnListBookCellPressed, OnLoginFragmentPressed, OnRegisterFragment, android.widget.SearchView.OnQueryTextListener {

    private var querySearch="none"


    override fun goLogin() {
        finish()
        val gotoLogin = Intent(this, MainActivity::class.java)
        startActivity(gotoLogin)
    }


    override fun goBack() {

        onBackPressed()
    }


    override fun isUserOnDataBase(currentUserId: Int) {
        CurrentUser.id = currentUserId
        val listBooks = ListBooksFragment.newInstance("none")
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer, listBooks).addToBackStack(null).commit()

    }

    override fun goToRegisterFragment(email: String, password: String) {
        val registerFragment = RegisterFragment.newInstance(email, password)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer, registerFragment).addToBackStack(null)
            .commit()
    }

    override fun onRegisterButtonPressed(name: String, email: String, Password: String) {
        val loginFragment = LoginFragment.newInstance(email, Password)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer, loginFragment).addToBackStack(null)
            .commit()
    }

    override fun onButton(books: Book) {

        val detailFragment = ShowDetailBookFragment.newInstance(books)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,detailFragment).addToBackStack(null).commit()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)


        search_books.setOnCloseListener {
            !search_books.isIconified
        }

        search_books.setOnQueryTextListener(this)



        if(savedInstanceState == null) {
            val LoginFrag = LoginFragment.newInstance("","")
            supportFragmentManager.beginTransaction().replace(R.id.maincontainer, LoginFrag).commit()
        }


    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        querySearch = p0.toString()
        val listBooksFragment = ListBooksFragment.newInstance(p0!!)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer, listBooksFragment).commit()
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {

        return false
    }


/*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }*/

}
