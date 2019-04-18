package com.example.library

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var listener: OnLoginFragmentPressed


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bt_login_loginfragment.setOnClickListener {
            listener.isUserOnDataBase()
        }

        bt_register_loginfragment.setOnClickListener {
            listener.goToRegisterFragment(et_email_loginfragment.text.toString(),et_password_registerfragment.text.toString())
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
        fun newInstance(email: String, password:String): LoginFragment{
            val loginFragment = LoginFragment()
            val args = Bundle()
            args.putString("email", email)
            args.putString("password", password)
            loginFragment.arguments = args

            return loginFragment
        }

    }
}
