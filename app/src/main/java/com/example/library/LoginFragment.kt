package com.example.library

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : androidx.fragment.app.Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var listener: onLogindPressed




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonLogin.setOnClickListener {
            listener.onLoginFragmentInteraction("hola")
        }

        buttonRegister.setOnClickListener {
            listener.onLoginFragmentRegister()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    /*
    fun onButtonPressed(user : User) {
        listener?.onRgisterFragment(user)
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is onLogindPressed) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement onRegisterFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = activity as onLogindPressed
    }


    interface onLogindPressed {
        fun onLoginFragmentInteraction(text: String)


        fun onLoginFragmentRegister()
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
