package com.example.library

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.library.RoomDataBase.UsersDatabase
import kotlinx.android.synthetic.main.fragment_login.*


private const val ARG_EMAIL = "email"
private const val ARG_PASSWORD = "password"

class LoginFragment : Fragment() {
    private lateinit var email: String
    private lateinit var password: String

    private lateinit var listener: OnLoginFragmentPressed


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL) ?: ""
            password = it.getString(ARG_PASSWORD) ?: ""
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        et_email_loginfragment.setText(email)
        et_password_loginfragment.setText(password)

        val userDao = UsersDatabase.getInstance(activity!!.applicationContext).userDao()

        bt_login_loginfragment.setOnClickListener {
            for (i in 1..4) {
                Thread {
                    if (userDao.isExistUser(et_email_loginfragment.text.toString(),et_password_loginfragment.text.toString()) > 0){
                        listener.isUserOnDataBase()
                        return@Thread
                    } else {
                        Toast.makeText(activity, "asdfasdf", Toast.LENGTH_SHORT).show()
                    }
                }.run()
            }
        }

        bt_register_loginfragment.setOnClickListener {
            listener.goToRegisterFragment(et_email_loginfragment.text.toString(),et_password_loginfragment.text.toString())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginFragmentPressed) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRegisterFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = activity as OnLoginFragmentPressed
    }


    interface OnLoginFragmentPressed {

        //Click en el boton de login que hacer una query para saber si el usuario existe o no
        fun isUserOnDataBase()

        //Click en el boton de registro y enviamos el email y password
        fun goToRegisterFragment(email: String, password: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(email: String, password: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                    putString(ARG_PASSWORD, password)
                }
            }
    }

}