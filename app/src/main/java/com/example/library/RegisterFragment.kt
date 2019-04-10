package com.example.library
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library.RoomDataBase.User
import com.example.library.RoomDataBase.UsersDatabase
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

private const val ARG_EMAIL = "email"
private const val ARG_PASSWORD = "password"


class RegisterFragment : androidx.fragment.app.Fragment() {


    private lateinit var email: String
    private lateinit var password: String


    private lateinit var listener: OnRegisterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL) ?: ""
            password = it.getString(ARG_PASSWORD) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        et_email_registerfragment.setText(email)
        et_password_registerfragment.setText(password)


        bt_register_registerfragment.setOnClickListener {


            val id: String = UUID.randomUUID().toString()
            var user = User(
                id,
                et_email_registerfragment.text.toString(),
                et_username_registerfragment.text.toString(),
                et_password_registerfragment.text.toString(),
                null
            )
            val userDao = UsersDatabase.getInstance(activity!!.applicationContext).userDao()
            userDao.insertUser(user)


        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegisterFragment) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRegisterFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = activity as OnRegisterFragment
    }

    interface OnRegisterFragment {
        fun onRegisterFragment(text: String)

        fun onRegisterButtonPressed(email: String, name: String, Password: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(email: String, password: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                    putString(ARG_PASSWORD, password)
                }
            }
    }
}
