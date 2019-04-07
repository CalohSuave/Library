package com.example.library
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.library.ListBooksFragment.OnButtonPressedListener
import com.example.library.LoginFragment.onLogindPressed
import com.example.library.RegisterFragment.onRegisterFragment
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity(), OnButtonPressedListener, onLogindPressed, onRegisterFragment{


    override fun onRgisterFragment(text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onLoginFragmentInteraction(text: String) {

        if (buttonRegister.isPressed) {
            val registerFragment = RegisterFragment()
            supportFragmentManager.beginTransaction().replace(R.id.maincontainer, registerFragment).addToBackStack(null)
                .commit()
        }else if(buttonLogin.isPressed){
            val listBooks = ListBooksFragment()
            supportFragmentManager.beginTransaction().replace(R.id.maincontainer, listBooks).addToBackStack(null).commit()
        }

    }


    override fun onButton(books: Book) {

        val detailFragment = ShowDetailBookFragment.newInstance(books)
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,detailFragment).addToBackStack(null).commit()


    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val LoginFrag = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.maincontainer,LoginFrag).commit()




    }


}
